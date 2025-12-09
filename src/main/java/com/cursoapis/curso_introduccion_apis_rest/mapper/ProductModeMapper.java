package com.cursoapis.curso_introduccion_apis_rest.mapper;

import com.cursoapis.curso_introduccion_apis_rest.entity.Product;
import com.cursoapis.curso_introduccion_apis_rest.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductModeMapper {
    private final ModelMapper modelMapper;

    public ProductModeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }

    public Product updateEntityFromDTO(Product existingProduct, ProductDTO dto) {
        modelMapper.map(dto, existingProduct);
        return existingProduct;
    }
}
