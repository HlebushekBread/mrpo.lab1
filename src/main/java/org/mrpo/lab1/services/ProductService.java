package org.mrpo.lab1.services;

import org.mrpo.lab1.models.Product;
import org.mrpo.lab1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByArticle(String article) {
        Optional<Product> product = productRepository.findByArticle(article);
        return product.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(String article, Product product) {
        product.setArticle(article);
        productRepository.save(product);
    }

    public void delete(String article) {
        productRepository.deleteById(article);
    }
}
