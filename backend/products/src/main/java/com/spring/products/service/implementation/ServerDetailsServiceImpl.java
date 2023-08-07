package com.spring.products.service.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import com.spring.products.entity.Orders;
import com.spring.products.entity.Product;
import com.spring.products.entity.ServerDetails;
import com.spring.products.repository.ServerDetailsRepo;
import com.spring.products.service.ServerDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;

@Service
@AllArgsConstructor
@Transactional
public class ServerDetailsServiceImpl implements ServerDetailsService {
    ServerDetailsRepo serverDetailsRepo;
    @Override
    public ServerDetails addServerDetails(ServerDetails serverDetails) {

        return serverDetailsRepo.save(serverDetails);
    }

    @Override
    public ServerDetails updateServerDetails(long id, ServerDetails serverDetails) {
        ServerDetails server = getServerDetailsById(id);
        server.setPhysicalServer(serverDetails.getPhysicalServer());
        server.setServerName(serverDetails.getServerName());
        server.setPassword(serverDetails.getPassword());
        server.setIpAddress(serverDetails.getIpAddress());
        server.setRamSize(server.getRamSize());
        server.setStatusServer(serverDetails.getStatusServer());
        server.setUserName(server.getUserName());
        return serverDetailsRepo.save(serverDetails);
    }

    @Override
    public Boolean deleteServerDetails(long id) {
         serverDetailsRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public List<ServerDetails> getAllServerDetails() {
        return serverDetailsRepo.findAll();
    }

    @Override
    public ServerDetails getServerDetailsById(long id) {
        return serverDetailsRepo.findById(id).get();
    }

    @Override
    public List<ServerDetails> getNotActiveServers() {
        return serverDetailsRepo.getNotActiveServers();
    }

    @Override
    public List<ServerDetails> getAllServersThatActive() {
        return serverDetailsRepo.getAllServersThatActive();
    }

    @Override
    public ResponseEntity<byte[]> generateReport() {
        try{


            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document doc = new Document();
            PdfWriter.getInstance(doc, baos);
            doc.open();
            // writer.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            //PdfContentByte cb = writer.getDirectContent();


            // create a Paragraph object and add the Chunk to it
                /*Paragraph info = new Paragraph(al.process(data), getFont("data"));
                info.setAlignment(Element.ALIGN_RIGHT);
                doc.add(info);*/

            PdfPTable table = new PdfPTable(7);

            table.setWidthPercentage(100);

            Stream.of("Server Name","IP Address","UserName","Password","Status","Physical Server","ramSize")
                    .forEach(columnTitle->{
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));

                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_CENTER);
                        table.addCell(header);
                    });
            List<ServerDetails> servers = getAllServerDetails();
            for (ServerDetails server: servers) {
                table.addCell(String.valueOf(server.getServerName()));
                table.addCell(String.valueOf(server.getIpAddress()));
                table.addCell(String.valueOf(server.getUserName()));
                table.addCell(String.valueOf(server.getPassword()));
                table.addCell(String.valueOf(server.getStatusServer()));
                table.addCell(String.valueOf(server.getPhysicalServer()));
                table.addCell(String.valueOf(server.getRamSize()));
            }
            doc.add(table);




            doc.close();
            System.out.println("hellllll");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            headers.setContentLength(baos.size());
            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
