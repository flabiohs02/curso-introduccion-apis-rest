package com.cursoapis.curso_introduccion_apis_rest.service;

import java.util.List;

import com.cursoapis.curso_introduccion_apis_rest.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO findByName(String name);

    boolean existsByName(String name);

    CategoryDTO save(CategoryDTO categoryDTO);

    void delete(Long id);

    List<CategoryDTO> findAll();

    CategoryDTO findById(Long id);

    CategoryDTO update(Long id, CategoryDTO categoryDTO);

}
