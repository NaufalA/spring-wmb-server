package com.warungmakanbahari.warungmakanbahari.features.menu;

import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.AddMenuRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuCategoryDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.dtos.MenuResponseDto;
import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPriceEntity;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceCreate;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceDelete;
import com.warungmakanbahari.warungmakanbahari.shared.services.ServiceGet;

import java.util.List;

public interface MenuService extends ServiceCreate<MenuResponseDto, AddMenuRequestDto>, ServiceGet<MenuResponseDto, Long>, ServiceDelete<Long> {
    List<MenuCategoryDto> getCategories();

    MenuPriceEntity getPriceById(Long id);
}
