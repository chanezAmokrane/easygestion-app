package com.crud.crud_springboot.Service;

import com.crud.crud_springboot.DTO.OrderDto;
import com.crud.crud_springboot.DTO.ProductDto;
import com.crud.crud_springboot.Exception.*;
import com.crud.crud_springboot.Models.Order;
import com.crud.crud_springboot.Models.Product;
import com.crud.crud_springboot.Repository.OrderRepository;
import com.crud.crud_springboot.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService
{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    public Order createOrder(OrderDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getClientName() == null || dto.getClientName().trim().isEmpty()) {
            errors.add("ClientName is required");
        }

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            errors.add("Quantity is required and  must be greater than 0");
        }

        if (dto.getDate() == null) {
            errors.add("Date is required");
        }
        if (dto.getProductIds() == null || dto.getProductIds().isEmpty()) {
            errors.add("At least one product ID is required");
        }

        if (!errors.isEmpty()) {
            throw new InvalidOrderException(String.join(", ", errors));
        }


        Order order = new Order();
        order.setclientName(dto.getClientName());
        order.setQuantity(dto.getQuantity());
        order.setDate(dto.getDate());
        // Récupère les produits concernés
        List<Product> products = productRepository.findAllById(dto.getProductIds());

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products not found for given IDs");
        }

        // Vérifie le stock disponible
        for (Product product : products) {
            if (product.getstockQuantity() < dto.getQuantity()) {
                throw new StockInsufficientException("Insufficient stock for product " + product.getLabel());
            }
            }
        // le stock a jour
        for (Product product : products) {
            product.setstockQuantity(product.getstockQuantity() - dto.getQuantity());
        }
        productRepository.saveAll(products);
        // Associe les produits à la commande
        order.setProducts(products);

        // Sauvegarde la commande
        return orderRepository.save(order);
    }

    public List<Order> GetOrders(){
        if (orderRepository.findAll().isEmpty()) {
            throw new OrderNotFoundException("Order Not Found or there are no orders ");
        }
        return orderRepository.findAll();
    }


    @Transactional
    public void deleteOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            // Restaurer le stock des produits liés à cette commande
            for (Product product : order.getProducts()) {
                product.setstockQuantity(product.getstockQuantity() + order.getQuantity());
            }
            productRepository.saveAll(order.getProducts());

            // Retirer cette commande de tous les produits associés
            order.getProducts().forEach(product -> product.getOrders().remove(order));

            // Vider la liste des produits dans la commande
            order.getProducts().clear();

            // Supprimer la commande
            orderRepository.delete(order);
        }
    }


    @Transactional
    public Order updateOrder(Long id, OrderDto dto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }

        List<String> errors = new ArrayList<>();

        if (dto == null) {
            throw new InvalidOrderException("Order data is required");
        }

        if (dto.getClientName() == null || dto.getClientName().trim().isEmpty()) {
            errors.add("ClientName is required");
        }

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            errors.add("Quantity must be greater than 0");
        }

        if (dto.getDate() == null) {
            errors.add("Date is required");
        }

        if (dto.getProductIds() == null || dto.getProductIds().isEmpty()) {
            errors.add("At least one product ID is required");
        }

        if (!errors.isEmpty()) {
            throw new InvalidOrderException(String.join(", ", errors));
        }

        Order order = optionalOrder.get();
        double oldQuantity = order.getQuantity();
        double  newQuantity = dto.getQuantity();

        // Rendre le stock des anciens produits
        for (Product oldProduct : order.getProducts()) {
            oldProduct.setstockQuantity(oldProduct.getstockQuantity() + oldQuantity);
        }

        // Charger les nouveaux produits
        List<Product> newProducts = productRepository.findAllById(dto.getProductIds());
        if (newProducts.isEmpty()) {
            throw new ProductNotFoundException("Products not found for given IDs");
        }
        // Vérifier stock
        for (Product newProduct : newProducts) {
            if (newProduct.getstockQuantity() < newQuantity) {
                throw new StockInsufficientException("Insufficient stock for product " + newProduct.getLabel());

            }
        }

        // Déduire le stock
        for (Product newProduct : newProducts) {
            newProduct.setstockQuantity(newProduct.getstockQuantity() - newQuantity);
        }

        productRepository.saveAll(newProducts);

        // Mettre à jour la commande
        order.setclientName(dto.getClientName());
        order.setQuantity(newQuantity);
        order.setDate(dto.getDate());
        order.setProducts(newProducts);

        return orderRepository.save(order);
    }



}
