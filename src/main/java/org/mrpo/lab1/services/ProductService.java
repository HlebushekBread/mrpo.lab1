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

    public List<String> findAllArticles() {
        return productRepository.findAllArticles();
    }

    public Product findByArticle(String article) {
        return productRepository.findById(article).orElse(null);
    }

    private String generateArticle() {
        String[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".split("");
        StringBuilder result;
        int randomIndex;
        do {
            result = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                randomIndex = (int) Math.floor(Math.random() * characters.length);
                result.append(characters[randomIndex]);
            }
        } while (findAllArticles().contains(result.toString()));
        return result.toString();
    }

    @Transactional
    public String save(ProductDto productDto) {
        Product product = new Product();
        if(!productDto.getArticle().equals("000000")) {
            product.setArticle(productDto.getArticle());
        } else {
            product.setArticle(generateArticle());
        }
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setAmount(productDto.getAmount());
        product.setDescription(productDto.getDescription());
        product.setUnit(unitService.findById(productDto.getUnitId()));
        product.setCategory(categoryService.findById(productDto.getCategoryId()));
        product.setManufacturer(manufacturerService.findById(productDto.getManufacturerId()));
        product.setProvider(providerService.findById(productDto.getProviderId()));

        productRepository.save(product);

        return product.getArticle();
    }

    @Transactional
    public void delete(String article) {
        productRepository.deleteByArticle(article);
    }
}
