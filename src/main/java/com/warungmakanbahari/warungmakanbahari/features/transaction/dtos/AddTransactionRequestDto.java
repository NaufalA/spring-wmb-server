package com.warungmakanbahari.warungmakanbahari.features.transaction.dtos;

import java.time.Instant;
import java.util.List;

public class AddTransactionRequestDto {
    private Instant transactionDate;
    private String customerName;
    private Long tableId;
    private List<AddTransactionDetailRequestDto> detailList;

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = Instant.parse(transactionDate);
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

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public List<AddTransactionDetailRequestDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<AddTransactionDetailRequestDto> detailList) {
        this.detailList = detailList;
    }
}
