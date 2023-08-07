package com.spring.products.repository;

import com.spring.products.entity.SystemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemInfoRepo extends JpaRepository<SystemInfo,Long> {
}
