package com.cursoapis.curso_introduccion_apis_rest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByIsActive(IsActive isActive);
}
