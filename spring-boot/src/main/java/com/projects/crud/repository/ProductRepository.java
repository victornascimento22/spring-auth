package com.projects.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.crud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Método para buscar produtos pelo nome
    List<Product> findByName(String name);
    List<Product> findAllByActiveTrue();
    Optional<Product> findByIdAndActiveTrue(int id);

    Optional<Product> findByNameAndActiveTrue(String name);

    

}
