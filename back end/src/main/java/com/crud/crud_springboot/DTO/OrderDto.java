package com.crud.crud_springboot.DTO;

import com.crud.crud_springboot.Models.Product;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private String clientName;
    private Double quantity;
    private List<Long> productIds;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }



}