package com.projects.crud.mapper;

import com.projects.crud.dtos.ProductRequestDTO;
import com.projects.crud.dtos.ProductResponseDTO;
import com.projects.crud.model.Category;
import com.projects.crud.model.Product;

public class ProductMapper {

    // Método atualizado para receber Category
    public static Product toEntity(ProductRequestDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category);
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory().getName()); // pega o nome da categoria
        dto.setInsertAt(product.getInsertAt());
        dto.setActive(product.isActive());
        return dto;
    }
}
