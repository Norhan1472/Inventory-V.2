package com.spring.products.repository;

import com.spring.products.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History,Long> {
    public List<History>findByAnyData(@Param("value") String value);
}
