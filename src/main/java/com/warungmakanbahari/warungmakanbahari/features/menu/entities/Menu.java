package com.warungmakanbahari.warungmakanbahari.features.menu.entities;

import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu extends NumberBaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MenuPrice> menuPrices = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuPrice> getMenuPrices() {
        return menuPrices;
    }

    public void setMenuPrices(List<MenuPrice> menuPrices) {
        this.menuPrices = menuPrices;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }
}