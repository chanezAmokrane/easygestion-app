package com.crud.crud_springboot.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String label;
    private Double stockQuantity;
    private Double price;
    private String type;
@ManyToMany (mappedBy = "products")
@JsonBackReference
private List<Order> orders = new ArrayList<>();

    public Long getProductId() {
        return product_id;
    }

    public void setProductId(Long id) {
        this.product_id = id;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Double getstockQuantity() {
        return stockQuantity;
    }

    public void setstockQuantity(Double quantity) {
        this.stockQuantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}


