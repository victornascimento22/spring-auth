package com.projects.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.crud.dtos.ProductRequestDTO;
import com.projects.crud.dtos.ProductResponseDTO;
import com.projects.crud.mapper.ProductMapper;
import com.projects.crud.model.Product;
import com.projects.crud.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        product.setActive(true); // Sempre começa como ativo
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }
    
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAllByActiveTrue();
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    public ProductResponseDTO getProductByID(int id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
            .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));
        return ProductMapper.toDTO(product);
    }
    
    public List<ProductResponseDTO> getProductByName(String name) {
        Optional<Product> products = productRepository.findByNameAndActiveTrue(name);
        return products.stream().map(ProductMapper::toDTO).toList();
    }
    
    public ProductResponseDTO updateProduct(int id, ProductRequestDTO dto) {
        Product existingProduct = productRepository.findByIdAndActiveTrue(id)
            .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));

        existingProduct.setName(dto.getName());
        existingProduct.setCategory(dto.getCategory());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStock(dto.getStock());

        existingProduct = productRepository.save(existingProduct);
        return ProductMapper.toDTO(existingProduct);
    }
    
    public void deleteProduct(int id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
            .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));
    
        product.setActive(false);
        productRepository.save(product);
    }

    public void enableProduct(int id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));
    
        product.setActive(true);
        productRepository.save(product);
    }
}
