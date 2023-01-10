package com.warungmakanbahari.warungmakanbahari.features.menu.implementations;

import com.warungmakanbahari.warungmakanbahari.features.menu.MenuService;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.AddMenuRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuCategoryDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuResponseDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuCategoryEntity;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuEntity;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPriceEntity;
import com.warungmakanbahari.warungmakanbahari.features.menu.repositories.MenuCategoryRepository;
import com.warungmakanbahari.warungmakanbahari.features.menu.repositories.MenuPriceRepository;
import com.warungmakanbahari.warungmakanbahari.features.menu.repositories.MenuRepository;
import com.warungmakanbahari.warungmakanbahari.shared.classes.GenericSpecificationBuilder;
import com.warungmakanbahari.warungmakanbahari.shared.classes.SearchCriteria;
import com.warungmakanbahari.warungmakanbahari.shared.constants.QueryOperator;
import com.warungmakanbahari.warungmakanbahari.shared.constants.SearchOperation;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuPriceRepository priceRepository;
    private final MenuCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public MenuServiceImpl(
            MenuRepository menuRepository,
            MenuPriceRepository priceRepository,
            MenuCategoryRepository categoryRepository,
            ModelMapper modelMapper
    ) {
        this.menuRepository = menuRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MenuResponseDto create(AddMenuRequestDto addMenuRequestDto) {
        Optional<MenuCategoryEntity> category = categoryRepository.findById(addMenuRequestDto.getMenuCategoryId());

        if (category.isEmpty()) {
            throw new NotFoundException("Category Data Not Found");
        }

        MenuEntity menu = new MenuEntity();
        menu.setName(addMenuRequestDto.getName());
        menu.setMenuCategory(category.get());

        menu = menuRepository.save(menu);

        MenuPriceEntity menuPrice = new MenuPriceEntity();
        menuPrice.setUnitPrice(addMenuRequestDto.getUnitPrice());
        menuPrice.setMenu(menu);

        menuPrice = priceRepository.save(menuPrice);

        menu.getMenuPrices().add(menuPrice);

        return mapToDto(menu, menuPrice);
    }

    public PagedResponse<MenuResponseDto> getAll(Integer page, Integer size, List<SearchCriteria> criteria) {
        Pageable pageable;
        if (size <= 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<MenuEntity> menuPage;
        if (criteria.size() > 0) {
            Specification<MenuEntity> specs =
                    new GenericSpecificationBuilder<MenuEntity>().build(criteria, this::menuCriteriaPath);
            menuPage = menuRepository.findAll(specs, pageable);
        } else {
            menuPage = menuRepository.findAll(pageable);
        }

        List<MenuResponseDto> menuResponses = menuPage.get().map(this::mapToDto).collect(Collectors.toList());

        return new PagedResponse<>(menuPage, menuResponses);
    }

    public MenuResponseDto getById(Long id) {
        Optional<MenuEntity> menu = menuRepository.findById(id);

        if (menu.isEmpty()) {
            throw new NotFoundException("Menu Data Not Found");
        }

        return mapToDto(menu.get());
    }

    @Override
    public PagedResponse<MenuResponseDto> getAll(Integer page, Integer size) {
        throw new RuntimeException("Unimplemented");
    }

    public Long delete(Long id) {
        Optional<MenuEntity> menu = menuRepository.findById(id);

        if (menu.isEmpty()) {
            throw new NotFoundException("Menu Data Not Found");
        }

        menu.get().setDeletedAt(Instant.now());

        return menuRepository.save(menu.get()).getId();
    }

    private MenuResponseDto mapToDto(MenuEntity menu) {
        MenuPriceEntity activePrice = getActivePrice(menu.getId());
        return new MenuResponseDto(
                menu.getId(),
                menu.getName(),
                activePrice.getId(),
                activePrice.getUnitPrice(),
                modelMapper.map(menu.getMenuCategory(), MenuCategoryDto.class)
        );
    }

    private MenuResponseDto mapToDto(MenuEntity menu, MenuPriceEntity activePrice) {
        return new MenuResponseDto(
                menu.getId(),
                menu.getName(),
                activePrice.getId(),
                activePrice.getUnitPrice(),
                modelMapper.map(menu.getMenuCategory(), MenuCategoryDto.class)
        );
    }

    private MenuPriceEntity getActivePrice(Long menuId) {
        Specification<MenuPriceEntity> specs = new GenericSpecificationBuilder<MenuPriceEntity>().build(
                List.of(
                        new SearchCriteria("menuId", menuId, QueryOperator.EQUALS, SearchOperation.AND),
                        new SearchCriteria("isActive", true, QueryOperator.EQUALS, SearchOperation.AND)
                ),
                this::priceCriteriaPath
        );

        Optional<MenuPriceEntity> activePrice = priceRepository.findOne(specs);

        if (activePrice.isEmpty()) {
            throw new NotFoundException("Price Data Not Found");
        }

        return activePrice.get();
    }

    private Path<String> menuCriteriaPath(Root<MenuEntity> root, SearchCriteria criteria) {
        if (criteria.getField().equals("categoryId")) {
            return menuJoinCategory(root).get("id");
        }

        return root.get(criteria.getField());
    }

    private Join<MenuEntity, MenuCategoryEntity> menuJoinCategory(Root<MenuEntity> root) {
        return root.join("menuCategory");
    }

    private Path<String> priceCriteriaPath(Root<MenuPriceEntity> root, SearchCriteria criteria) {
        if (criteria.getField().equals("menuId")) {
            return priceJoinMenu(root).get("id");
        }

        return root.get(criteria.getField());
    }

    private Join<MenuPriceEntity, MenuEntity> priceJoinMenu(Root<MenuPriceEntity> root) {
        return root.join("menu");
    }

    @Override
    public List<MenuCategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(category -> new MenuCategoryDto(
                category.getId(),
                category.getName()
        )).collect(Collectors.toList());
    }

    @Override
    public MenuPriceEntity getPriceById(Long id) {
        Optional<MenuPriceEntity> price = priceRepository.findById(id);

        if (price.isEmpty()) {
            throw new NotFoundException("Menu Data Not Found");
        }

        return price.get();
    }
}
