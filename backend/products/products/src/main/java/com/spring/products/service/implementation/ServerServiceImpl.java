package com.spring.products.service.implementation;

import com.spring.products.entity.Server;
import com.spring.products.repository.ServerRepo;
import com.spring.products.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static com.spring.products.enumeration.StatusServer.SERVER_DOWN;
import static com.spring.products.enumeration.StatusServer.SERVER_UP;
import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepo serverRepo;
    @Override
    public Server createNewServer(Server server) {
        log.info("Add new Server {}",server.getName());
        server.setImageUrl(setImageUrl());
        return serverRepo.save(server);
    }

    public String setImageUrl() {
        String serverNames[]={"server1.png","server2.png","server3.png","server4.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/server/image/"+ serverNames[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Server updateServer(Server server) {
        log.info("Updating Server {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Server getServerById(long id) {
        log.info("Fetching Server By id {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Collection<Server> getAllServers(int limit) {
        log.info("Fetching all Servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Boolean deleteById(long id) {
        log.info("Deleting Server by id {}",id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        Server server = serverRepo.findByIpAddress(ipAddress);
        System.out.println("Hellll");
        System.out.println(server);
        InetAddress address = InetAddress.getByName(ipAddress);
        //System.out.println(address);
        server.setStatusServer(address.isReachable(10000)?SERVER_UP:SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }
}
