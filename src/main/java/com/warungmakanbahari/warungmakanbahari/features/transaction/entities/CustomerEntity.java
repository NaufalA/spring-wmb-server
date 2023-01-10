package com.warungmakanbahari.warungmakanbahari.features.transaction.entities;

import com.warungmakanbahari.warungmakanbahari.features.auth.entities.UserEntity;
import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity extends NumberBaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}