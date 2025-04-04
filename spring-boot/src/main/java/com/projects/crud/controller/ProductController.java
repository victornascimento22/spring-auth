package com.projects.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.crud.dtos.ProductRequestDTO;
import com.projects.crud.dtos.ProductResponseDTO;
import com.projects.crud.services.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO createdProduct = productService.createProduct(dto);
        return ResponseEntity.ok(createdProduct);
    }

    // 🔹 Listar todos os produtos ativos
    @GetMapping("getAll")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 🔹 Buscar produto por ID
    @GetMapping("/getByID/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable int id) {
        ProductResponseDTO product = productService.getProductByID(id);
        return ResponseEntity.ok(product);
    }

    // 🔹 Buscar produto por nome
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable String name) {
        List<ProductResponseDTO> products = productService.getProductByName(name);
        return ResponseEntity.ok(products);
    }

    // 🔹 Atualizar um produto existente
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    // 🔹 Inativar (soft delete) um produto
    @DeleteMapping("/inative/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Reativar um produto inativo
    @PutMapping("/enable/{id}")
    public ResponseEntity<Void> enableProduct(@PathVariable int id) {
        productService.enableProduct(id);
        return ResponseEntity.noContent().build();
    }
}
