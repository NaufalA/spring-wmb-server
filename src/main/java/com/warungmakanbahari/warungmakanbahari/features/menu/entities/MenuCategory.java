package com.warungmakanbahari.warungmakanbahari.features.menu.entities;

import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_categories")
public class MenuCategory extends NumberBaseEntity {
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "menuCategory", orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}