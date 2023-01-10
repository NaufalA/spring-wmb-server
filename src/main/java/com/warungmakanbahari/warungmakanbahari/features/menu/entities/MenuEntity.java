package com.warungmakanbahari.warungmakanbahari.features.menu.entities;

import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class MenuEntity extends NumberBaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MenuPriceEntity> menuPrices = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategoryEntity menuCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuPriceEntity> getMenuPrices() {
        return menuPrices;
    }

    public void setMenuPrices(List<MenuPriceEntity> menuPrices) {
        this.menuPrices = menuPrices;
    }

    public MenuCategoryEntity getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategoryEntity menuCategory) {
        this.menuCategory = menuCategory;
    }
}