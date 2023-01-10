package com.warungmakanbahari.warungmakanbahari.features.table.dtos;

public class AddTableRequestDto {
    private String name;
    private Boolean availability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
