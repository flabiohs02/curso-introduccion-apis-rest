package com.cursoapis.curso_introduccion_apis_rest.service;

import java.util.List;

import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;

public interface ProductService {

    List<Product> findAll();

    Product findByName(String name);

    Product findByIdProduct(Long id);

    List<Product> findByIsActive(IsActive isActive);

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product updateProductIsActive(Long id, IsActive isActive);

    void deleteProduct(Long id);
}
