package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.dtos.OrderDto;
import org.mrpo.lab1.dtos.OrderProductDto;
import org.mrpo.lab1.dtos.UserOrderDto;
import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.models.OrderProduct;
import org.mrpo.lab1.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
    private final AddressService addressService;
    private final StatusService statusService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    private int generateReceiveCode() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Long> findAllIds() {
        return orderRepository.findAllIds();
    }

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public long make(UserOrderDto userOrderDto) {
        Order order = new Order();

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto orderProductDto : userOrderDto.getOrderProductDtos()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(
                    productService.findByArticle(orderProductDto.getProductArticle())
            );
            orderProduct.setAmount(orderProductDto.getAmount());
            orderProducts.add(orderProduct);
        }

        order.setReceiveCode(generateReceiveCode());

        order.setOrderProducts(orderProducts);
        order.setOrderDate(new Date());
        order.setDeliveryDate(Date.from(LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant()));
        order.setStatus(statusService.findById(1));
        order.setAddress(addressService.findById(userOrderDto.getAddressId()));
        order.setUser(userService.findById(userOrderDto.getUserId()));

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public long save(OrderDto orderDto) {
        Order order = new Order();

        if (orderDto.getId() != 0) {
            order.setId(orderDto.getId());
        }

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto orderProductDto : orderDto.getOrderProductDtos()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(
                    productService.findByArticle(orderProductDto.getProductArticle())
            );
            orderProduct.setAmount(orderProductDto.getAmount());
            orderProducts.add(orderProduct);
        }

        if (orderDto.getReceiveCode() != 0) {
            order.setReceiveCode(orderDto.getReceiveCode());
        } else {
            order.setReceiveCode(generateReceiveCode());
        }

        order.setOrderProducts(orderProducts);
        order.setOrderDate(orderDto.getOrderDate());
        order.setDeliveryDate(orderDto.getDeliveryDate());
        order.setStatus(statusService.findById(orderDto.getStatusId()));
        order.setAddress(addressService.findById(orderDto.getAddressId()));
        order.setUser(userService.findById(orderDto.getUserId()));

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
