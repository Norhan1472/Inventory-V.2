
package com.spring.products.service;

import com.spring.products.entity.SystemInfo;
import com.spring.products.entity.SystemInfo;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

public interface SystemInfoService {
        SystemInfo createNewSystem(SystemInfo systemInfo);
        SystemInfo updateSystemInfo(long id,SystemInfo SystemInfo);
        SystemInfo getSystemInfoById(long id);
        List<SystemInfo> getAllSystemInfos();
        Boolean deleteById(long id);

    ResponseEntity<byte[]> generateReport();
}


