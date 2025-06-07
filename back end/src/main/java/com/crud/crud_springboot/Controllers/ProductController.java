package com.crud.crud_springboot.Controllers;


import com.crud.crud_springboot.DTO.ProductDto;
import com.crud.crud_springboot.Models.Product;

import com.crud.crud_springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@Valid
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private  ProductService productService;


        //public ProductController(ProductService productService) {
          //  this.productService = productService;
        //}


        // Méthode POST pour créer un produit
        @PostMapping
        public ResponseEntity<Product>  createProduct(@Valid @RequestBody ProductDto dto) {
            Product product = productService.createProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }

        @GetMapping
        public ResponseEntity<List<Product> > getAllProducts() {
       List<Product>  products=productService.GetProducts();
       return ResponseEntity.ok(products);

        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
        Product updatedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }





    }
