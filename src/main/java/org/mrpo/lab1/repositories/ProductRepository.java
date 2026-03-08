package org.mrpo.lab1.repositories;

import org.mrpo.lab1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT article FROM Product")
    List<String> findAllArticles();

    void deleteByArticle(String article);
}
