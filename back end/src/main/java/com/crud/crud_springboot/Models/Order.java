package com.crud.crud_springboot.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String clientName;
    private Double quantity;
    private LocalDate date;
    @ManyToMany
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name=" product_id")
    )
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();




    public Long getOrder_id() {
        return orderId;
    }

    public void setOrder_id(Long id) {
        this.orderId = id;
    }


    public String getClientName() {
        return clientName;
    }

    public void setclientName(String clientName) {
        this.clientName = clientName;
    }


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
       public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }



}



