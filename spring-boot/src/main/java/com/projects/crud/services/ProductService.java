package com.projects.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.crud.dtos.ProductRequestDTO;
import com.projects.crud.dtos.ProductResponseDTO;
import com.projects.crud.mapper.ProductMapper;
import com.projects.crud.model.Category;
import com.projects.crud.model.Product;
import com.projects.crud.repository.CategoryRepository;
import com.projects.crud.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Category category = categoryRepository.findByName(dto.getCategory())
            .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory()));

        Product product = ProductMapper.toEntity(dto, category);
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

        Category category = categoryRepository.findByName(dto.getCategory())
            .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory()));

        existingProduct.setName(dto.getName());
        existingProduct.setCategory(category);
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
