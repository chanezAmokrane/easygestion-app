package com.crud.crud_springboot.Repository;

import com.crud.crud_springboot.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
