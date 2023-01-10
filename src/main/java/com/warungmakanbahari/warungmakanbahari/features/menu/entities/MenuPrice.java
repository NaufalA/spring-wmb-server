package com.warungmakanbahari.warungmakanbahari.features.menu.entities;

import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "menu_prices")
public class MenuPrice extends NumberBaseEntity {
    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public MenuPrice() {
        isActive = true;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}