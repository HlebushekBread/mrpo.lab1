package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.dtos.ProductDto;
import org.mrpo.lab1.models.*;
import org.mrpo.lab1.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    private final UnitService unitService;
    private final ProviderService providerService;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByArticle(String article) {
        return productRepository.findByArticle(article).orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(String article, ProductDto productDto) {
        Product product = findByArticle(article);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setAmount(productDto.getAmount());
        product.setDescription(productDto.getDescription());
        if (productDto.getImage() != null) {
            product.setImage(productDto.getImage());
        }
        product.setUnit(unitService.findById(productDto.getUnitId()));
        product.setCategory(categoryService.findById(productDto.getCategoryId()));
        product.setManufacturer(manufacturerService.findById(productDto.getManufacturerId()));
        product.setProvider(providerService.findById(productDto.getProviderId()));
    }

    public void delete(String article) {
        productRepository.deleteById(article);
    }
}
