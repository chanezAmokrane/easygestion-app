package com.crud.crud_springboot.Service;

import com.crud.crud_springboot.DTO.ProductDto;
import com.crud.crud_springboot.Exception.DuplicateProductException;
import com.crud.crud_springboot.Exception.InvalidProductException;
import com.crud.crud_springboot.Exception.ProductNotFoundException;
import com.crud.crud_springboot.Models.Product;
import com.crud.crud_springboot.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDto dto) {

        Optional<Product> existingProduct = productRepository.findByLabel((dto.getLabel()));
        if (existingProduct.isPresent()) {
            throw new DuplicateProductException("A product with the label '" + dto.getLabel() + "' already exists");
        }
        Product product = new Product();
        product.setLabel(dto.getLabel());
        product.setstockQuantity(dto.getStockQuantity());
        product.setPrice(dto.getPrice());
        product.setType(dto.getType());

        return productRepository.save(product);
    }

    public List<Product> GetProducts(){
       if (productRepository.findAll().isEmpty()) {
           throw new ProductNotFoundException("Products not found or there are no products");
       }
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Produit non trouvé avec l'ID " + id);
        }
            Product product = optionalProduct.get();
            // Retirer ce produit de toutes les commandes associées
            product.getOrders().forEach(order -> order.getProducts().remove(product));
            product.getOrders().clear();

            // Supprimer le produit
            productRepository.delete(product);


    }
    public Product updateProduct(Long id, ProductDto dto) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        if (dto.getLabel() == null || dto.getLabel().isEmpty()) {
            throw new InvalidProductException("Label is required");
        }

        if (dto.getType() == null || dto.getType().isEmpty()) {
            throw new InvalidProductException("Type is required");
        }

        if (dto.getPrice() == null || dto.getPrice() <= 0) {
            throw new InvalidProductException("Price is required and must be greater than 0");
        }
        if (dto.getStockQuantity() == null || dto.getStockQuantity() <= 0) {
            throw new InvalidProductException("Stock quantity is required and must be greater than 0");
        }

        Product product = optionalProduct.get(); // maintenant c’est sûr, il existe

        product.setLabel(dto.getLabel());
        product.setstockQuantity(dto.getStockQuantity());
        product.setPrice(dto.getPrice());
        product.setType(dto.getType());

        return productRepository.save(product);
    }

}
