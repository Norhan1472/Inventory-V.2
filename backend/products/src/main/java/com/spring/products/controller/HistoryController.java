package com.spring.products.controller;

import com.lowagie.text.DocumentException;
import com.spring.products.entity.History;
import com.spring.products.service.HistoryService;
import com.spring.products.util.PdfGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/history/v3")
@AllArgsConstructor
@CrossOrigin("*")
public class HistoryController {
    private final HistoryService historyService;
    @PostMapping("addHistory")
    public ResponseEntity<History> addHistory(@RequestBody History history){
        return new ResponseEntity<>(historyService.addOrderHistory(history), HttpStatus.OK);
    }
    //api/history/v1/getAllHistories
    @GetMapping("getAllHistories")
    public ResponseEntity<List<History>>getAllHistories(){
        return new ResponseEntity<>(historyService.getAllHistories(),HttpStatus.OK);
    }
    @GetMapping("getByHistoryId/{id}")
    public ResponseEntity<History>getByHistoryId(@PathVariable("id") long id){
        return new ResponseEntity<>(historyService.getHistoryById(id),HttpStatus.OK);
    }
    @GetMapping("getByAnyThing/value/{value}")
    public ResponseEntity<List<History>>getByAnyThing(@PathVariable String value){

        return new ResponseEntity<>(historyService.findByAnyThing(value),HttpStatus.OK);
    }
    //api/history/v1/export-to-pdf
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=history" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < History > listofHistories = historyService.getAllHistories();
        PdfGenerator generator = new PdfGenerator();
        generator.generate(listofHistories, response);
    }
}
