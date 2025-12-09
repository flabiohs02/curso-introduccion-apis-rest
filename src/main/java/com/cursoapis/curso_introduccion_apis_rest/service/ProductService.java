package com.cursoapis.curso_introduccion_apis_rest.service;

import java.util.List;

import com.cursoapis.curso_introduccion_apis_rest.dto.ProductDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findByName(String name);

    ProductDTO findByIdProduct(Long id);

    List<ProductDTO> findByIsActive(IsActive isActive);

    ProductDTO saveProduct(ProductDTO product);

    ProductDTO updateProduct(Long id, ProductDTO product);

    ProductDTO updateProductIsActive(Long id, IsActive isActive);

    void deleteProduct(Long id);
}
