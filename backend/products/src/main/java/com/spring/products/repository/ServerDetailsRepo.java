package com.spring.products.repository;

import com.spring.products.entity.Product;
import com.spring.products.entity.ServerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerDetailsRepo extends JpaRepository<ServerDetails,Long> {
    @Query("from ServerDetails s where s.statusServer = 0")
    List<ServerDetails> getAllServersThatActive();
    @Query("from ServerDetails s where s.statusServer = 1")
    List<ServerDetails>getNotActiveServers();
}
