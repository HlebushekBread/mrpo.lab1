package org.mrpo.lab1.repositories;

import org.mrpo.lab1.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT id FROM Order")
    List<Long> findAllIds();

    List<Order> findAllByUserId(long userId);
}
