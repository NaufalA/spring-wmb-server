package com.warungmakanbahari.warungmakanbahari.features.menu;

import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.AddMenuRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuResponseDto;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.CommonResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.PagedResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        PagedResponse<MenuResponseDto> response = menuService.getAll(page, size);

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
}
