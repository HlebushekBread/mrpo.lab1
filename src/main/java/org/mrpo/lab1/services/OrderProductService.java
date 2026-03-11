package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.repositories.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public List<Long> findOrderIdsByProductArticle(String article) {
        return orderProductRepository.findOrderIdsByProductArticle(article);
    }
}
