package com.spring.products.service;

import com.spring.products.entity.History;

import java.util.List;

public interface HistoryService {
    public History addOrderHistory(History history);
    public List<History>getAllHistories();
    public History getHistoryById(long id);
    public List<History>findByAnyThing(String value);
}
