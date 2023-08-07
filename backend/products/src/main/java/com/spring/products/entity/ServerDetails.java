package com.spring.products.entity;

import com.spring.products.enumeration.Status;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServerDetails extends BaseEntity {
    private String physicalServer;
    private String serverName;
    private String ipAddress;
    private String userName;
    private String password;
    private Status statusServer = Status.ACTIVE;
    private String ramSize;

}
