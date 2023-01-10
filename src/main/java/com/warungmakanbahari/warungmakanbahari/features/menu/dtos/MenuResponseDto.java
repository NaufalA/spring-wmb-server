package com.warungmakanbahari.warungmakanbahari.features.menu.dtos;

public class MenuResponseDto {
    private Long id;
    private String name;
    private Long priceId;
    private Float unitPrice;
    private MenuCategoryDto menuCategory;

    public MenuResponseDto(Long id, String name, Long priceId, Float unitPrice, MenuCategoryDto menuCategory) {
        this.id = id;
        this.name = name;
        this.priceId = priceId;
        this.unitPrice = unitPrice;
        this.menuCategory = menuCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MenuCategoryDto getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategoryDto menuCategory) {
        this.menuCategory = menuCategory;
    }
}
