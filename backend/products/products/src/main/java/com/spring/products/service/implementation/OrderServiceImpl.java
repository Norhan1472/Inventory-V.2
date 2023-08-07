package com.spring.products.service.implementation;

import com.spring.products.entity.Orders;
import com.spring.products.entity.Product;
import com.spring.products.exception.OrderNotFoundException;
import com.spring.products.repository.OrderRepo;
import com.spring.products.service.OrderService;
import com.spring.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;
    @Override
    public Orders addOrder(Orders order) {
        return orderRepo.save(order);
    }

    @Override
    public Orders updateOrder(long orderId, Orders order) {
        Orders checkExist = getOrderById(orderId);
        checkExist.setEmpName(order.getEmpName());
        checkExist.setNationalId(order.getNationalId());
        checkExist.setEmail(order.getEmail());
        checkExist.setDescription(order.getDescription());
        checkExist.setProduct(order.getProduct());
        return orderRepo.save(checkExist);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Orders getOrderById(long orderId) {
        return orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order isn't exist with this id "+orderId));
    }

    @Override
    public Boolean deleteOrderById(long orderId) {
        orderRepo.deleteById(orderId);
        return TRUE;
    }

    @Override
    public Orders assignOrderWithProduct(long orderId, String productSerialNumber) {
        Set<Product>products=new HashSet<>();
        Product productExist = productService.getProductById(productSerialNumber);
        products.add(productExist);
        Orders checkExistOrders = getOrderById(orderId);
        checkExistOrders.setProduct(products);
        return addOrder(checkExistOrders);
    }
    @Override
    public Set<Product> getAllProductsOfOrder(long orderId) {
        Orders order = getOrderById(orderId);
        return order.getProduct();
    }
}
