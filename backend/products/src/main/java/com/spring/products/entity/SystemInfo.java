package com.spring.products.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfo extends BaseEntity {
    private String userName;
    private String password;
    private String softwareName;

}
