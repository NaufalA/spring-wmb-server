package com.warungmakanbahari.warungmakanbahari.features.menu;

import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.AddMenuRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuCategoryDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuResponseDto;
import com.warungmakanbahari.warungmakanbahari.shared.classes.SearchCriteria;
import com.warungmakanbahari.warungmakanbahari.shared.constants.QueryOperator;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.CommonResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.MENU_URL)
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> addMenu(@RequestBody AddMenuRequestDto dto) {
        MenuResponseDto response = menuService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.CREATED),
                "Success Add Menu",
                response
        ));
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "categoryId", required = false) Long categoryId
    ) {
        PagedResponse<MenuResponseDto> response;

        List<SearchCriteria> criteria = new ArrayList<>();
        if (categoryId != null) {
            criteria.add(new SearchCriteria("categoryId", categoryId, QueryOperator.EQUALS));
        }
        response = menuService.getAll(page, size, criteria);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get All Menu",
                response
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable("id") Long id) {
        MenuResponseDto response = menuService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get Menu",
                response
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable("id") Long id) {
        Long response = menuService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Delete Menu",
                response
        ));
    }

    @GetMapping("/categories")
    public ResponseEntity<CommonResponse> getCategories() {
        List<MenuCategoryDto> response = menuService.getCategories();

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK),
                "Success Get All Menu Category",
                response
        ));
    }
}
