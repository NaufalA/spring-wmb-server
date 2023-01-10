package com.warungmakanbahari.warungmakanbahari.features.menu.repositories;

import com.warungmakanbahari.warungmakanbahari.features.menu.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
