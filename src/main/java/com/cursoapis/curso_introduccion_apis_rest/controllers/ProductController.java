package com.cursoapis.curso_introduccion_apis_rest.controllers;

import com.cursoapis.curso_introduccion_apis_rest.dto.ProductDTO;
import com.cursoapis.curso_introduccion_apis_rest.entity.IsActive;
import com.cursoapis.curso_introduccion_apis_rest.entity.Product;
import com.cursoapis.curso_introduccion_apis_rest.mapper.ProductMapper;
import com.cursoapis.curso_introduccion_apis_rest.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findByIdProduct(@PathVariable Long id) {
        Product product = productService.findByIdProduct(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDTO> findByName(@PathVariable String name) {
        Product product = productService.findByName(name);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping("/is-active/{isActive}")
    public ResponseEntity<List<ProductDTO>> findByIsActive(@PathVariable IsActive isActive) {
        List<ProductDTO> products = productService.findByIsActive(isActive).stream()
                .map(productMapper::toDTO)
                .toList();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product productSaved = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDTO(productSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        Product existingProduct = productService.findByIdProduct(id);
        Product updatedProduct = productMapper.updateEntityFromDTO(existingProduct, productDTO);
        Product savedProduct = productService.saveProduct(updatedProduct);
        return ResponseEntity.ok(productMapper.toDTO(savedProduct));
    }

    @PutMapping("/{id}/is-active")
    public ResponseEntity<ProductDTO> updateProductIsActive(
            @PathVariable Long id,
            @RequestBody IsActive isActive) {
        Product product = productService.updateProductIsActive(id, isActive);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
