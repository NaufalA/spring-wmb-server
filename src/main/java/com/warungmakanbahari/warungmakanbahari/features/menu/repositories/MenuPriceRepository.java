package com.warungmakanbahari.warungmakanbahari.features.menu.repositories;

import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuPriceRepository extends JpaRepository<MenuPriceEntity, Long>, JpaSpecificationExecutor<MenuPriceEntity> {
}
