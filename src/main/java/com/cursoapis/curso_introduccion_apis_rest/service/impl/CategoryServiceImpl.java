package com.cursoapis.curso_introduccion_apis_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cursoapis.curso_introduccion_apis_rest.service.CategoryService;
import com.cursoapis.curso_introduccion_apis_rest.exception.DuplicateResourceException;
import com.cursoapis.curso_introduccion_apis_rest.exception.ResourceNotFoundException;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;
import com.cursoapis.curso_introduccion_apis_rest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", name));
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public Category save(Category category) {
        if (existsByName(category.getName())) {
            throw new DuplicateResourceException("Category", "name", category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category categoryExist = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(categoryExist);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public Category update(Long id, Category category) {
        Category categoryExist = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryExist.setName(category.getName());
        return categoryRepository.save(categoryExist);
    }

}
