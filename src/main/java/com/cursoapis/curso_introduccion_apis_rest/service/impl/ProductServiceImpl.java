package com.cursoapis.curso_introduccion_apis_rest.service.impl;

import com.cursoapis.curso_introduccion_apis_rest.exception.ResourceNotFoundException;
import com.cursoapis.curso_introduccion_apis_rest.mapper.ProductModeMapper;
import com.cursoapis.curso_introduccion_apis_rest.dto.ProductDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;
import com.cursoapis.curso_introduccion_apis_rest.repositories.ProductRepository;
import com.cursoapis.curso_introduccion_apis_rest.service.ProductService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductModeMapper productModeMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductModeMapper productModeMapper) {
        this.productRepository = productRepository;
        this.productModeMapper = productModeMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productModeMapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO findByName(String name) {
        Product productOptional = productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "name", name));
        return productModeMapper.toDTO(productOptional);
    }

    @Override
    public ProductDTO findByIdProduct(Long id) {
        Product productOptional = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productModeMapper.toDTO(productOptional);
    }

    @Override
    public List<ProductDTO> findByIsActive(IsActive isActive) {
        List<Product> products = productRepository.findByIsActive(isActive);
        return products.stream()
                .map(productModeMapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product productoEntity = productModeMapper.toEntity(productDTO);
        return productModeMapper.toDTO(productRepository.save(productoEntity));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product productExist = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Product productUpdate = productModeMapper.updateEntityFromDTO(productExist, productDTO);
        return productModeMapper.toDTO(productRepository.save(productUpdate));
    }

    @Override
    public ProductDTO updateProductIsActive(Long id, IsActive isActive) {
        Product productOptional = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productOptional.setIsActive(isActive);
        Product productUpdate = productRepository.save(productOptional);
        return productModeMapper.toDTO(productUpdate);
    }

    @Override
    public void deleteProduct(Long id) {
        Product productExist = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(productExist);
    }

}
