package com.cursoapis.curso_introduccion_apis_rest.service.impl;

import com.cursoapis.curso_introduccion_apis_rest.exception.ResourceNotFoundException;
import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;
import com.cursoapis.curso_introduccion_apis_rest.repositories.ProductRepository;
import com.cursoapis.curso_introduccion_apis_rest.service.ProductService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "name", name));
    }

    @Override
    public Product findByIdProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public List<Product> findByIsActive(IsActive isActive) {
        return productRepository.findByIsActive(isActive);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productExist = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productExist.setName(product.getName());
        productExist.setPrice(product.getPrice());
        productExist.setQuantity(product.getQuantity());
        productExist.setDescription(product.getDescription());
        productExist.setCategory(product.getCategory());
        productExist.setIsActive(product.getIsActive());
        return productRepository.save(productExist);
    }

    @Override
    public Product updateProductIsActive(Long id, IsActive isActive) {
        Product productOptional = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productOptional.setIsActive(isActive);
        return productRepository.save(productOptional);
    }

    @Override
    public void deleteProduct(Long id) {
        Product productExist = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(productExist);
    }

}
