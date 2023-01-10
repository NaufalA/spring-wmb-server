package com.warungmakanbahari.warungmakanbahari.features.transaction.dtos;

import java.time.Instant;
import java.util.List;

public class TransactionDto {
    private Long id;
    private Instant transactionDate;
    private String customerName;
    private String tableName;
    private List<TransactionDetailDto> detailList;

    public TransactionDto() {
    }

    public TransactionDto(
            Long id,
            Instant transactionDate,
            String customerName,
            String tableName
    ) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.customerName = customerName;
        this.tableName = tableName;
    }

    public TransactionDto(
            Long id,
            Instant transactionDate,
            String customerName,
            String tableName,
            List<TransactionDetailDto> detailList
    ) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.customerName = customerName;
        this.tableName = tableName;
        this.detailList = detailList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TransactionDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<TransactionDetailDto> detailList) {
        this.detailList = detailList;
    }
}
