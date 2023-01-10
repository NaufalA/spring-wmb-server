package com.warungmakanbahari.warungmakanbahari.features.table.entities;

import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tables")
public class TableEntity extends NumberBaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "availability", nullable = false)
    private Boolean availability = true;

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}