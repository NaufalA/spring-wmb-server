package com.warungmakanbahari.warungmakanbahari.features.menu.repositories;

import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>, JpaSpecificationExecutor<MenuEntity> {
}
