package com.spring.products.service;

import com.spring.products.entity.Brand;
import com.spring.products.entity.Product;
import com.spring.products.entity.ServerDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServerDetailsService {
    public ServerDetails addServerDetails(ServerDetails serverDetails);

    public ServerDetails updateServerDetails(long id, ServerDetails serverDetails);

    public Boolean deleteServerDetails(long id);

    public List<ServerDetails> getAllServerDetails();

    public ServerDetails getServerDetailsById(long id);

    List<ServerDetails> getNotActiveServers();

    List<ServerDetails> getAllServersThatActive();

    public ResponseEntity<byte[]> generateReport();
}
