package com.chronex.product.service;

import com.chronex.product.dto.ProductRequest;
import com.chronex.product.dto.ProductResponse;
import com.chronex.product.entity.Product;
import com.chronex.product.exception.ProductNotFoundException;
import com.chronex.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisTemplate<String, ProductResponse> productRedisTemplate;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = mapToEntity(request);
        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    public ProductResponse getProductById(Long id) {
        String key = "product:" + id;

        ProductResponse cachedProduct = productRedisTemplate.opsForValue().get(key);
        if (cachedProduct != null) {
            return cachedProduct;
        }

        Product product = findProductById(id);
        ProductResponse response = mapToResponse(product);

        productRedisTemplate.opsForValue().set(
                key,
                response,
                Duration.ofMinutes(10)
        );

        return response;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existingProduct = findProductById(id);

        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setGender(request.getGender());
        existingProduct.setImageUrl(request.getImageUrl());
        existingProduct.setMovement(request.getMovement());
        existingProduct.setGlassMaterial(request.getGlassMaterial());
        existingProduct.setCaseMaterial(request.getCaseMaterial());
        existingProduct.setCaseShape(request.getCaseShape());
        existingProduct.setCaseDiameter(request.getCaseDiameter());
        existingProduct.setDialColor(request.getDialColor());
        existingProduct.setStrapMaterial(request.getStrapMaterial());
        existingProduct.setStrapColor(request.getStrapColor());
        existingProduct.setWaterResistance(request.getWaterResistance());
        existingProduct.setWarrantyPeriod(request.getWarrantyPeriod());

        Product updatedProduct = productRepository.save(existingProduct);
        ProductResponse response = mapToResponse(updatedProduct);

        String key = "product:" + id;
        productRedisTemplate.opsForValue().set(
                key,
                response,
                Duration.ofMinutes(10)
        );

        return response;
    }

    public void deleteProduct(Long id) {
        Product existingProduct = findProductById(id);
        productRepository.delete(existingProduct);

        String key = "product:" + id;
        productRedisTemplate.delete(key);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    private Product mapToEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .gender(request.getGender())
                .imageUrl(request.getImageUrl())
                .movement(request.getMovement())
                .glassMaterial(request.getGlassMaterial())
                .caseMaterial(request.getCaseMaterial())
                .caseShape(request.getCaseShape())
                .caseDiameter(request.getCaseDiameter())
                .dialColor(request.getDialColor())
                .strapMaterial(request.getStrapMaterial())
                .strapColor(request.getStrapColor())
                .waterResistance(request.getWaterResistance())
                .warrantyPeriod(request.getWarrantyPeriod())
                .build();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .gender(product.getGender())
                .imageUrl(product.getImageUrl())
                .movement(product.getMovement())
                .glassMaterial(product.getGlassMaterial())
                .caseMaterial(product.getCaseMaterial())
                .caseShape(product.getCaseShape())
                .caseDiameter(product.getCaseDiameter())
                .dialColor(product.getDialColor())
                .strapMaterial(product.getStrapMaterial())
                .strapColor(product.getStrapColor())
                .waterResistance(product.getWaterResistance())
                .warrantyPeriod(product.getWarrantyPeriod())
                .build();
    }
}
