package com.warungmakanbahari.warungmakanbahari.features.menu.implementations;

import com.warungmakanbahari.warungmakanbahari.features.menu.MenuService;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.AddMenuRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuCategoryDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuResponseDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.Menu;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuCategory;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPrice;
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
        Optional<MenuCategory> category = categoryRepository.findById(addMenuRequestDto.getMenuCategoryId());

        if (category.isEmpty()) {
            throw new NotFoundException("Category Data Not Found");
        }

        Menu menu = new Menu();
        menu.setName(addMenuRequestDto.getName());
        menu.setMenuCategory(category.get());

        menu = menuRepository.save(menu);

        MenuPrice menuPrice = new MenuPrice();
        menuPrice.setUnitPrice(addMenuRequestDto.getUnitPrice());
        menuPrice.setMenu(menu);

        menuPrice = priceRepository.save(menuPrice);

        menu.getMenuPrices().add(menuPrice);

        return mapToDto(menu, menuPrice);
    }

    public PagedResponse<MenuResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable;
        if (size <= 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Menu> menuPage = menuRepository.findAll(pageable);

        List<MenuResponseDto> menuResponses = menuPage.get().map(this::mapToDto).collect(Collectors.toList());

        return new PagedResponse<>(menuPage, menuResponses);
    }

    public MenuResponseDto getById(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);

        if (menu.isEmpty()) {
            throw new NotFoundException("Menu Data Not Found");
        }

        return mapToDto(menu.get());
    }

    public Long delete(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);

        if (menu.isEmpty()) {
            throw new NotFoundException("Menu Data Not Found");
        }

        menu.get().setDeletedAt(Instant.now());

        return menuRepository.save(menu.get()).getId();
    }

    private MenuResponseDto mapToDto(Menu menu) {
        MenuPrice activePrice = getActivePrice(menu.getId());
        return new MenuResponseDto(
                menu.getId(),
                menu.getName(),
                activePrice.getUnitPrice(),
                modelMapper.map(menu.getMenuCategory(), MenuCategoryDto.class)
        );
    }

    private MenuResponseDto mapToDto(Menu menu, MenuPrice activePrice) {
        return new MenuResponseDto(
                menu.getId(),
                menu.getName(),
                activePrice.getUnitPrice(),
                modelMapper.map(menu.getMenuCategory(), MenuCategoryDto.class)
        );
    }

    private MenuPrice getActivePrice(Long menuId) {
        Specification<MenuPrice> specs = new GenericSpecificationBuilder<MenuPrice>().build(
                List.of(
                        new SearchCriteria("menuId", menuId, QueryOperator.EQUALS, SearchOperation.AND),
                        new SearchCriteria("isActive", true, QueryOperator.EQUALS, SearchOperation.AND)
                ),
                this::criteriaPath
        );

        Optional<MenuPrice> activePrice = priceRepository.findOne(specs);

        if (activePrice.isEmpty()) {
            throw new NotFoundException("Price Data Not Found");
        }

        return activePrice.get();
    }

    private Path<String> criteriaPath(Root<MenuPrice> root, SearchCriteria criteria) {
        if (criteria.getField().equals("menuId")) {
            return menuJoin(root).get("id");
        }

        return root.get(criteria.getField());
    }

    private Join<MenuPrice, Menu> menuJoin(Root<MenuPrice> root) {
        return root.join("menu");
    }
}