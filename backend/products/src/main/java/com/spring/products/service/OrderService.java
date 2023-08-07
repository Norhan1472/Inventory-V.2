package com.spring.products.service;

import com.spring.products.entity.Orders;
import com.spring.products.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {
    public Orders addOrder(Orders order);
    public Orders updateOrder(long orderId,Orders order);
    public List<Orders>getAllOrders();
    public Orders getOrderById(long orderId);
    public Boolean deleteOrderById(long orderId,String reasonReturn);
    public Orders assignOrderWithProduct(long orderId,long productId);
    public List<Product> getAllProductsOfOrder(long orderId);
    public boolean deleteOneItemOfOrder(long orderId,long productId,String reason);
    public Orders addProductsToOrder(long orderId, ArrayList<Product> products);
    ResponseEntity<byte[]> generateReport(String orderId);
}
