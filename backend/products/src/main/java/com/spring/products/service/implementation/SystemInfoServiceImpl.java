package com.spring.products.service.implementation;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.products.entity.ServerDetails;
import com.spring.products.entity.SystemInfo;
import com.spring.products.repository.SystemInfoRepo;
import com.spring.products.service.SystemInfoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SystemInfoServiceImpl implements SystemInfoService {
    SystemInfoRepo systemInfoRepo;

    @Override
    public SystemInfo createNewSystem(SystemInfo systemInfo) {
        return systemInfoRepo.save(systemInfo);
    }

    @Override
    public SystemInfo updateSystemInfo(long id,SystemInfo systemInfo) {
        SystemInfo system = getSystemInfoById(id);
        system.setPassword(systemInfo.getPassword());
        system.setSoftwareName(systemInfo.getSoftwareName());
        system.setUserName(systemInfo.getUserName());
        return systemInfoRepo.save(system);
    }

    @Override
    public SystemInfo getSystemInfoById(long id) {
        return systemInfoRepo.findById(id).get();
    }

    @Override
    public List<SystemInfo> getAllSystemInfos() {
        return systemInfoRepo.findAll();
    }

    @Override
    public Boolean deleteById(long id) {
        systemInfoRepo.deleteById(id);
        return TRUE;
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

            PdfPTable table = new PdfPTable(3);

            table.setWidthPercentage(100);

            Stream.of("Software","userName","Password")
                    .forEach(columnTitle->{
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));

                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_CENTER);
                        table.addCell(header);
                    });
            List<SystemInfo> systems = getAllSystemInfos();
            for (SystemInfo system: systems) {
                table.addCell(String.valueOf(system.getSoftwareName()));
                table.addCell(String.valueOf(system.getUserName()));
                table.addCell(String.valueOf(system.getPassword()));
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
