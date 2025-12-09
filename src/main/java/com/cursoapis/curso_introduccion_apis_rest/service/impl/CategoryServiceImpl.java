package com.cursoapis.curso_introduccion_apis_rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cursoapis.curso_introduccion_apis_rest.service.CategoryService;
import com.cursoapis.curso_introduccion_apis_rest.exception.DuplicateResourceException;
import com.cursoapis.curso_introduccion_apis_rest.exception.ResourceNotFoundException;
import com.cursoapis.curso_introduccion_apis_rest.mapper.CategoryModelMapper;
import com.cursoapis.curso_introduccion_apis_rest.dto.CategoryDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;
import com.cursoapis.curso_introduccion_apis_rest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryModelMapper categoryModelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryModelMapper categoryModelMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryModelMapper = categoryModelMapper;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", name));
        return categoryModelMapper.toDTO(category);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (existsByName(categoryDTO.getName())) {
            throw new DuplicateResourceException("Category", "name", categoryDTO.getName());
        }
        Category category = categoryModelMapper.toEntity(categoryDTO);
        return categoryModelMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        Category categoryExist = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(categoryExist);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryModelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return categoryModelMapper.toDTO(category);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category categoryExist = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryExist.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(categoryExist);
        return categoryModelMapper.toDTO(savedCategory);
    }

}
