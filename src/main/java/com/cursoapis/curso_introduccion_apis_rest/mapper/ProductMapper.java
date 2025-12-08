package com.cursoapis.curso_introduccion_apis_rest.mapper;

import org.springframework.stereotype.Component;

import com.cursoapis.curso_introduccion_apis_rest.dto.ProductDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.Category;
import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;
import com.cursoapis.curso_introduccion_apis_rest.exception.ResourceNotFoundException;
import com.cursoapis.curso_introduccion_apis_rest.repositories.CategoryRepository;

/**
 * Mapper for converting between Product entity and ProductDTO.
 */
@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Converts a Product entity to ProductDTO.
     * 
     * @param product the Product entity
     * @return ProductDTO
     */
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .isActive(product.getIsActive() != null ? product.getIsActive().name() : null)
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }

    /**
     * Converts a ProductDTO to Product entity.
     * 
     * @param dto the ProductDTO
     * @return Product entity
     */
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice() != null ? dto.getPrice() : 0.0);
        product.setQuantity(dto.getQuantity() != null ? dto.getQuantity() : 0);
        product.setIsActive(dto.getIsActive() != null ? IsActive.valueOf(dto.getIsActive()) : IsActive.ACTIVE);

        // Set category if categoryId is provided
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));
            product.setCategory(category);
        }

        return product;
    }

    /**
     * Updates an existing Product entity with data from ProductDTO.
     * 
     * @param existingProduct the existing Product entity to update
     * @param dto             the ProductDTO with new data
     * @return updated Product entity
     */
    public Product updateEntityFromDTO(Product existingProduct, ProductDTO dto) {
        if (dto == null) {
            return existingProduct;
        }

        if (dto.getName() != null) {
            existingProduct.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existingProduct.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            existingProduct.setPrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            existingProduct.setQuantity(dto.getQuantity());
        }
        if (dto.getIsActive() != null) {
            existingProduct.setIsActive(IsActive.valueOf(dto.getIsActive()));
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));
            existingProduct.setCategory(category);
        }

        return existingProduct;
    }
}
