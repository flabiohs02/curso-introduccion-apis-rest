package com.cursoapis.curso_introduccion_apis_rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
