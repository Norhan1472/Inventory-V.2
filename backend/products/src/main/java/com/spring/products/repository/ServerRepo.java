package com.spring.products.repository;

import com.spring.products.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepo extends JpaRepository<Server,Long> {
     public Server findByIpAddress(String ipAddress);
}
