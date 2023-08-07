package com.spring.products.controller;

import com.spring.products.entity.Orders;
import com.spring.products.entity.Product;
import com.spring.products.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/order/v3")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("addOrder")
    public ResponseEntity<Orders>addOrder(@RequestBody Orders order){
        return ResponseEntity.ok(orderService.addOrder(order));
    }
    //api/order/v3/updateOrder/{orderId}
    @PutMapping("updateOrder/{orderId}")
    public ResponseEntity<Orders>updateOrder(@PathVariable long orderId,@RequestBody Orders order){
        return ResponseEntity.ok(orderService.updateOrder(orderId,order));
    }
    @GetMapping("getOrderById/{orderId}")
    public ResponseEntity<Orders>getOrderById(@PathVariable long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    @GetMapping("getAllOrders")
    public ResponseEntity<List<Orders>>getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    //api/order/v1/deleteOrderById/{orderId}/reason/{reasonReturn}
    @DeleteMapping("deleteOrderById/{orderId}/reason/{reasonReturn}")
    public ResponseEntity<Boolean>deleteOrderById(@PathVariable long orderId,@PathVariable String reasonReturn){
        return ResponseEntity.ok(orderService.deleteOrderById(orderId,reasonReturn));
    }
    @PutMapping("assignOrderWithProduct/{orderId}/product/{productId}")
    public ResponseEntity<Orders>assignOrderWithProduct(@PathVariable long orderId,@PathVariable long productId){
        return ResponseEntity.ok(orderService.assignOrderWithProduct(orderId,productId));
    }
    @GetMapping("getAllProductsOfOrder/{orderId}")
    public ResponseEntity<List<Product>>getAllProductsOfOrder(@PathVariable long orderId){
        return ResponseEntity.ok(orderService.getAllProductsOfOrder(orderId));
    }
    //api/order/v1/deleteOneItemOfOrder/order/{orderId}/product/{productId}/reason/{reason}
    @DeleteMapping("deleteOneItemOfOrder/order/{orderId}/product/{productId}/reason/{reason}")
    public ResponseEntity<Boolean>deleteOneItemOfOrder(@PathVariable long orderId,@PathVariable long productId,@PathVariable String reason){
        return ResponseEntity.ok(orderService.deleteOneItemOfOrder(orderId,productId,reason));
    }
    //api/order/v1/addProductToItem/order/{orderId}
    @PutMapping("addProductToItem/order/{orderId}")//Set<Object>
    public ResponseEntity<Orders>addProductsToOrder(@PathVariable long orderId,@RequestBody Product products){
        System.out.println(products.getProductName());
        return null;// ResponseEntity.ok(orderService.addProductsToOrder(orderId,products));
    }
    //api/order/v1/generateReport
    @GetMapping("generateReport/{orderId}")
    public ResponseEntity<byte[]> generateReport(@PathVariable String orderId){
        try{
            return orderService.generateReport(orderId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<byte[]>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
