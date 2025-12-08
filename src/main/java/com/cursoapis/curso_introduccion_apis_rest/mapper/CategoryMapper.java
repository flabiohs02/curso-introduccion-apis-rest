package com.cursoapis.curso_introduccion_apis_rest.mapper;

import org.springframework.stereotype.Component;

import com.cursoapis.curso_introduccion_apis_rest.dto.CategoryDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;

@Component
public class CategoryMapper {

    /**
     * Convierte una entidad Category a CategoryDTO
     * 
     * @param category la entidad Category
     * @return CategoryDTO
     */
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    /**
     * Convierte un CategoryDTO a entidad Category
     * 
     * @param dto el CategoryDTO
     * @return Category entity
     */
    public Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
