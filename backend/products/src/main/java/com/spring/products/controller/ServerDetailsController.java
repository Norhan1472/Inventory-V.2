package com.spring.products.controller;

import com.spring.products.entity.Product;
import com.spring.products.entity.ServerDetails;
import com.spring.products.service.ServerDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/serverDetails/v3")
@CrossOrigin(origins = "*")
public class ServerDetailsController {
    ServerDetailsService serverDetailsService;
    //api/ServerDetails/v3/addServerDetails
    @PostMapping("addServerDetails")
    public ResponseEntity<ServerDetails> addServerDetails(@RequestBody ServerDetails serverDetails) {
        System.out.println("llkkjj");
        System.out.println(serverDetails.getStatusServer());
        serverDetailsService.addServerDetails(serverDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("getAllServerDetails")
    public List<ServerDetails> getAllServerDetailss(){
        return serverDetailsService.getAllServerDetails();
    }
    @GetMapping("getServerDetailsById/{id}")
    public ResponseEntity<ServerDetails> getServerDetailsById(@PathVariable long id){
        return ResponseEntity.ok(serverDetailsService.getServerDetailsById(id));
    }
    @DeleteMapping("deleteServerDetailsById/{id}")
    public ResponseEntity<Boolean> deleteServerDetailsById(@PathVariable long id){
        return ResponseEntity.ok(serverDetailsService.deleteServerDetails(id));
    }
    @PutMapping("updateServerDetails/{id}")
    public ResponseEntity<ServerDetails> updateServerDetails(@PathVariable long id,@RequestBody ServerDetails serverDetails){
        return ResponseEntity.ok(serverDetailsService.updateServerDetails(id,serverDetails));
    }
    @GetMapping("getNotActiveServers")
    public List<ServerDetails>getNotActiveServers(){
        return serverDetailsService.getNotActiveServers();
    }
    @GetMapping("getAllServersThatActive")
    public List<ServerDetails>getAllServersThatActive(){
        return serverDetailsService.getAllServersThatActive();
    }
    @GetMapping("generateReport")
    public ResponseEntity<byte[]> generateReport(){
        try{
            return serverDetailsService.generateReport();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<byte[]>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
