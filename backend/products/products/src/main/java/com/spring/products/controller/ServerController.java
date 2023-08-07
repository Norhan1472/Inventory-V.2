package com.spring.products.controller;

import com.spring.products.entity.Response;
import com.spring.products.entity.Server;
import com.spring.products.service.ServerService;
import com.spring.products.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.spring.products.enumeration.StatusServer.SERVER_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("server")
@CrossOrigin(origins = "*")
public class ServerController {
    private final ServerService serverService;
    private final ServerServiceImpl serverServiceImpl;
    @GetMapping("getAllServers")
    public ResponseEntity<Response>getAllServers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        //throw new InterruptedException("Something went wrong");
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Servers retrieved")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .data(Map.of("Servers",serverService.getAllServers(3000)))
                        .build()
        );
    }
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response>ping(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .message(server.getStatusServer()==SERVER_UP?"Ping Success":"Ping Failed")
                        .timeStamp(now())
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .data(Map.of("server",server))
                        .build()
        );
    }
    @PostMapping("AddNewServer")
    public ResponseEntity<Response>AddNewServer(@RequestBody @Valid Server server){
        Server addedServer= serverService.createNewServer(server);

        return ResponseEntity.ok(
                Response.builder()
                        .message("Server Created")
                        .statusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .timeStamp(now())
                        .data(Map.of("server",addedServer))
                        .build()

        );
    }
    @GetMapping("getServerById/{id}")
    public ResponseEntity<Response>getServerById(@PathVariable("id") long id){
        Server server = serverService.getServerById(id);
        return ResponseEntity.ok(
                Response.builder()
                        .httpStatus(OK)
                        .timeStamp(now())
                        .message(server==null?"failed to fetch server":"Fetched server")
                        .statusCode(OK.value())
                        .data(Map.of("Fetching server",server))
                        .build()
        );
    }
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Response>deleteServer(@PathVariable("id") long id){
        return ResponseEntity.ok(
                Response.builder()
                        .message("Server Deleted")
                        .statusCode(OK.value())
                        .httpStatus(OK)
                        .timeStamp(now())
                        .data(Map.of("server",serverService.deleteById(id)))
                        .build()
        );
    }
    @GetMapping(path = "image/{fileName}",produces = IMAGE_PNG_VALUE)
    public byte[] serverImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Desktop/images/"+fileName));
    }

}
