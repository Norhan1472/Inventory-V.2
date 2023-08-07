package com.spring.products.service.implementation;

import com.spring.products.entity.History;
import com.spring.products.repository.HistoryRepo;
import com.spring.products.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepo historyRepo;
    @Override
    public History addOrderHistory(History history) {

        return historyRepo.save(history);
    }

    @Override
    public List<History> getAllHistories() {
        return historyRepo.findAll();
    }

    @Override
    public History getHistoryById(long id) {
        return historyRepo.findById(id).get();
    }

    @Override
    public List<History> findByAnyThing( String value) {
        List<History>data= historyRepo.findByAnyData(value);
        System.out.println(data);
        return data;
    }
}
