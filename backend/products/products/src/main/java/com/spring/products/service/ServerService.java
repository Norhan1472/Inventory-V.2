package com.spring.products.service;

import com.spring.products.entity.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
     Server createNewServer(Server server);
     Server updateServer(Server server);
     Server getServerById(long id);
     Collection<Server>getAllServers(int limit);
     Boolean deleteById(long id);
     Server ping(String ipAddress) throws IOException;

}
