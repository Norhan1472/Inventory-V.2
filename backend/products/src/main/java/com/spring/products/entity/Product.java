package com.spring.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.products.enumeration.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Product.countNotActiveProducts",query = "SELECT COUNT(*) FROM Product p where p.status = 1 ")

@NamedQuery(name = "Product.countActiveProducts",query = "SELECT COUNT(*) FROM Product p where p.status = 0")

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String productName;
    private String type;
    private String Model;
    private String specification;
    private Status status = Status.ACTIVE;
    @Column(unique = true)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "fk_brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category category;
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Orders> orders = new ArrayList<>();

}