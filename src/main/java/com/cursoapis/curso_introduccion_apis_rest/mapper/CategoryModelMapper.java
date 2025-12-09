package com.cursoapis.curso_introduccion_apis_rest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.cursoapis.curso_introduccion_apis_rest.dto.CategoryDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;

@Component
public class CategoryModelMapper {
    private final ModelMapper modelMapper;

    public CategoryModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO toDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category toEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }

    public Category updateEntityFromDTO(Category existingCategory, CategoryDTO dto) {
        modelMapper.map(dto, existingCategory);
        return existingCategory;
    }
}
