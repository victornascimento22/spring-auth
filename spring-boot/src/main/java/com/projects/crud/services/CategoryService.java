package com.projects.crud.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.crud.dtos.CategoryDTO;
import com.projects.crud.mapper.CategoryMapper;
import com.projects.crud.model.Category;
import com.projects.crud.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = CategoryMapper.toEntity(dto);
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                         .map(CategoryMapper::toDTO).toList();
    }
}
