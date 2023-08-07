package com.spring.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedQuery(name="History.findByAnyData",query = "SELECT h FROM History h WHERE LOWER(h.empName) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(h.serialNumber) LIKE LOWER(CONCAT('%', :value, '%'))")

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private long historyId;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "serialNumber")
    private String serialNumber;
    @Column(name = "brand")
    private String brand;
    @Column(name = "product_desc")
    private String productDesc;
    @Column(name = "date_received")
    private Timestamp dateReceived;
    @Column(name = "date_retrieved")
    private Timestamp dateRetrieved;
    @Column(name = "reason")
    private String reason;
}
