package com.spring.products.enumeration;

public enum StatusServer {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");
    private String StatusServer;

    StatusServer(String statusServer) {
        StatusServer = statusServer;
    }

    public String getStatusServer() {
        return StatusServer;
    }
}
