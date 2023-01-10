package com.warungmakanbahari.warungmakanbahari.features.menu.repositories;

import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
