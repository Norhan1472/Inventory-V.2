package com.spring.products.entity;

import com.spring.products.enumeration.StatusServer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "ipAddress shouldn't be null or empty")
    private String ipAddress;
    private String memory;
    private String type;
    private String imageUrl;
    private StatusServer statusServer;
}
