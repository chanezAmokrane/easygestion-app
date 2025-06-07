package com.crud.crud_springboot.Controllers;

import com.crud.crud_springboot.DTO.OrderDto;
import com.crud.crud_springboot.Models.Order;
import com.crud.crud_springboot.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*") // ou le port utilisé par ton front

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

        @PostMapping
        public ResponseEntity<Order> createOrder(@RequestBody OrderDto dto) {
            Order createdOrder = orderService.createOrder(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        }

        @GetMapping
        public ResponseEntity<List<Order>> getAllOrders() {
            List<Order> orders = orderService.GetOrders();
            return ResponseEntity.ok(orders);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteOrder(@PathVariable long id) {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        }

        @PutMapping("/{id}")
        public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderDto dto) {
            Order updatedOrder = orderService.updateOrder(id, dto);
            return ResponseEntity.ok(updatedOrder);
        }



}






