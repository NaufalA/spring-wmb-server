package com.warungmakanbahari.warungmakanbahari.features.transaction.entities;

import com.warungmakanbahari.warungmakanbahari.features.table.entities.TableEntity;
import com.warungmakanbahari.warungmakanbahari.shared.entities.NumberBaseEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "transactions")
public class TransactionEntity extends NumberBaseEntity {
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_entity_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.PERSIST)
    private List<TransactionDetailEntity> transactionDetails;

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    public List<TransactionDetailEntity> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetailEntity> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}