package com.warungmakanbahari.warungmakanbahari.features.transaction.dtos;

public class AddTransactionDetailRequestDto {
    private Float quantity;
    private Long menuPriceId;

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getMenuPriceId() {
        return menuPriceId;
    }

    public void setMenuPriceId(Long menuPriceId) {
        this.menuPriceId = menuPriceId;
    }
}
