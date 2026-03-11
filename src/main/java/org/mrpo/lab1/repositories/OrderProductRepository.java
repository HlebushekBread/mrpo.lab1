package org.mrpo.lab1.repositories;

import org.mrpo.lab1.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @NativeQuery("SELECT op.order_id FROM order_product op WHERE op.product_article = :product_article")
    List<Long> findOrderIdsByProductArticle(@Param("product_article") String productArticle);
}
