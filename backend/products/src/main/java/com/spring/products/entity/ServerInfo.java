package com.spring.products.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/*@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "server_info")
@DynamicUpdate
@DynamicInsert*/
public class ServerInfo implements Serializable {
   // private static long serialVersionUID  = 1L;
}
