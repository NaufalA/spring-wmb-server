package com.warungmakanbahari.warungmakanbahari.features.transaction.dtos;

public class TransactionDetailDto {
    private final Long id;
    private final Float quantity;
    private final Long menuId;
    private final String menuName;
    private final Float unitPrice;
    private final Float Price;

    public TransactionDetailDto(Long id, Float quantity, Long menuId, String menuName, Float unitPrice, Float price) {
        this.id = id;
        this.quantity = quantity;
        this.menuId = menuId;
        this.menuName = menuName;
        this.unitPrice = unitPrice;
        Price = price;
    }

    public Long getId() {
        return id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Float getPrice() {
        return Price;
    }
}
