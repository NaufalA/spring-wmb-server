package com.warungmakanbahari.warungmakanbahari.features.transaction.entities;

import com.warungmakanbahari.warungmakanbahari.features.menu.entities.MenuPriceEntity;
import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "transaction_details")
public class TransactionDetailEntity extends NumberBaseEntity {
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_price_id", nullable = false)
    private MenuPriceEntity menuPrice;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public MenuPriceEntity getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(MenuPriceEntity menuPrice) {
        this.menuPrice = menuPrice;
    }
}