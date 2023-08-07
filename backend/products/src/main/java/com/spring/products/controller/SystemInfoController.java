package com.spring.products.controller;

import com.spring.products.entity.SystemInfo;
import com.spring.products.entity.SystemInfo;
import com.spring.products.service.SystemInfoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/systemInfo/v3")
@CrossOrigin(origins = "*")
public class SystemInfoController {
    SystemInfoService systemInfoService;
    //api/SystemInfo/v3/addSystemInfo
    @PostMapping("addSystemInfo")
    public ResponseEntity<SystemInfo> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        systemInfoService.createNewSystem(systemInfo);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("getAllSystemInfo")
    public List<SystemInfo> getAllSystemInfos(){
        return systemInfoService.getAllSystemInfos();
    }
    @GetMapping("getSystemInfoById/{id}")
    public ResponseEntity<SystemInfo> getSystemInfoById(@PathVariable long id){
        return ResponseEntity.ok(systemInfoService.getSystemInfoById(id));
    }
    @DeleteMapping("deleteSystemInfoById/{id}")
    public ResponseEntity<Boolean> deleteSystemInfoById(@PathVariable long id){
        return ResponseEntity.ok(systemInfoService.deleteById(id));
    }
    @PutMapping("updateSystemInfo/{id}")
    public ResponseEntity<SystemInfo> updateSystemInfo(@PathVariable long id,@RequestBody SystemInfo systemInfo){
        return ResponseEntity.ok(systemInfoService.updateSystemInfo(id,systemInfo));
    }
    @GetMapping("generateReport")
    public ResponseEntity<byte[]> generateReport(){
        try{
            return systemInfoService.generateReport();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<byte[]>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
