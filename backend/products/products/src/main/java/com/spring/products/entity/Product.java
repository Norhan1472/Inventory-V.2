package com.spring.products.entity;

import com.spring.products.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private long productId;
    @Id
    private String productSerialNumber;
    @Column(unique = true)
    private String productName;
    private String type;
    private String Model;
    private String specification;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "fk_brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category category;
}