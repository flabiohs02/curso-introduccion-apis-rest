package com.cursoapis.curso_introduccion_apis_rest.service;

import java.util.List;

import com.cursoapis.curso_introduccion_apis_rest.entity.Category;

public interface CategoryService {

    Category findByName(String name);

    boolean existsByName(String name);

    Category save(Category category);

    void delete(Long id);

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, Category category);

}
