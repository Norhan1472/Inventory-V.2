package com.spring.products.entity;

import lombok.*;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@CrossOrigin(origins = "*")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(unique = true)
    private String categoryName;
    @ManyToMany
    @JoinTable(name = "category_brand",
            joinColumns =
                    @JoinColumn(name = "category_id"),

            inverseJoinColumns =
                    @JoinColumn(name = "brand_Id")
            )
    private Set<Brand> brands =new HashSet<>();
}
