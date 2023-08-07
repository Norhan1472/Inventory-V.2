package com.spring.products.service.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.pdf.fonts.otf.Language;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import com.lowagie.text.Cell;
import com.spring.products.entity.History;
import com.spring.products.entity.Orders;
import com.spring.products.entity.Product;
import com.spring.products.enumeration.Status;
import com.spring.products.exception.OrderNotFoundException;
import com.spring.products.repository.OrderRepo;
import com.spring.products.service.HistoryService;
import com.spring.products.service.OrderService;
import com.spring.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import static com.spring.products.enumeration.Status.ACTIVE;
import static com.spring.products.enumeration.Status.NOT_ACTIVE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final HistoryService historyService;

    @Override
    public Orders addOrder(Orders order) {

        for(int i=0;i<order.getProducts().size();i++){
            Product product= productService.getProductById(order.getProducts().get(i).getProductId());
            product.setStatus(NOT_ACTIVE);
            productService.updateProduct(order.getProducts().get(i).getProductId(),product);
            //add history
            History history= new History();
            history.setEmpName(order.getEmpName());
            Calendar cal = Calendar.getInstance();
            history.setDateReceived(new Timestamp(cal.getTime().getTime()));
            history.setProductName(product.getProductName());
            history.setBrand(product.getBrand().getBrandName());
            System.out.println("here");
            System.out.println(product.getSpecification());
            history.setProductDesc(product.getSpecification());
            //history.setProductDesc(product.getSpecification());
            history.setSerialNumber(product.getSerialNumber());
            historyService.addOrderHistory(history);
        }

        return orderRepo.save(order);

    }

    @Override
    public Orders updateOrder(long orderId, Orders order) {
        Orders checkExist = getOrderById(orderId);
        /*for(Product p:checkExist.getProducts()){
            Product product= productService.getProductById(p.getProductId());
            product.setStatus(ACTIVE);
            productService.updateProduct(p.getProductId(),product);
        }*/
        checkExist.setEmpName(order.getEmpName());
        checkExist.setNationalId(order.getNationalId());
        checkExist.setEmail(order.getEmail());
        checkExist.setDescription(order.getDescription());
       // checkExist.setProducts(order.getProducts());
        Orders orderss = addOrder(checkExist);
        return orderss;
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Orders getOrderById(long orderId) {
        return orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order isn't exist with this id "+orderId));
    }

    @Override
    public Boolean deleteOrderById(long orderId,String reasonReturn) {
        Orders order = getOrderById(orderId);

        List<Product>products=new ArrayList<>();
        products = order.getProducts();
        for( Product p : products){
            p.setStatus(ACTIVE);
            History history = new History();
            history.setEmpName(order.getEmpName());
            Calendar cal = Calendar.getInstance();
            history.setDateRetrieved(new Timestamp(cal.getTime().getTime()));
            history.setReason(reasonReturn);
            history.setProductName(p.getProductName());
            history.setProductDesc(p.getSpecification());
            history.setBrand(p.getBrand().getBrandName());
            history.setSerialNumber(p.getSerialNumber());
            historyService.addOrderHistory(history);
        }
        List<Product>productss=new ArrayList<>();
        order.setProducts(productss);

        orderRepo.deleteById(orderId);

        return TRUE;
    }

    @Override
    public Orders assignOrderWithProduct(long orderId, long productId) {
        List<Product>products = new ArrayList<>();
        Product productExist = productService.getProductById(productId);
        Orders checkExistOrders = getOrderById(orderId);
        products = checkExistOrders.getProducts();
        products.add(productExist);
        checkExistOrders.setProducts(products);
        return addOrder(checkExistOrders);
    }
    @Override
    public List<Product> getAllProductsOfOrder(long orderId) {
        Orders order = getOrderById(orderId);
        return order.getProducts();
    }

    @Override
    public boolean deleteOneItemOfOrder(long orderId, long productId, String reasonReturn) {
        Orders order = getOrderById(orderId);
        List<Product>products = order.getProducts();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId()==productId){
                Product p = order.getProducts().get(i) ;
                History history = new History();
                history.setEmpName(order.getEmpName());
                Calendar cal = Calendar.getInstance();
                history.setDateRetrieved(new Timestamp(cal.getTime().getTime()));
                history.setReason(reasonReturn);
                history.setProductName(p.getProductName());
                history.setProductDesc(p.getSpecification());
                history.setBrand(p.getBrand().getBrandName());
                history.setSerialNumber(p.getSerialNumber());
                historyService.addOrderHistory(history);
                order.getProducts().remove(i) ;
                orderRepo.save(order);
                //Product product= productService.getProductById(p.getProductId());
                p.setStatus(ACTIVE);
                productService.updateProduct(p.getProductId(),p);
                return true;
            }
        }
        return false;
    }

    @Override
    public Orders addProductsToOrder(long orderId,ArrayList<Product> products) {
        Orders order = getOrderById(orderId);
        for(Product product :products){
            order.getProducts().add(product);
            orderRepo.save(order);
        }
        return order;
    }
    @Override
    public ResponseEntity<byte[]> generateReport(String orderId) {
        try{
                String fileName = getUUID();
            System.out.println("hellllllppp");
            System.out.println(orderId);
                Orders order = getOrderById(Long.parseLong((orderId)));

                LanguageProcessor al = new ArabicLigaturizer();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                Document doc = new Document();
                PdfWriter writer =  PdfWriter.getInstance(doc,new FileOutputStream(fileName+".pdf"));
                PdfWriter.getInstance(doc, baos);
                doc.open();
               // writer.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                //PdfContentByte cb = writer.getDirectContent();
                Paragraph paragraph = new Paragraph(al.process("إستلام عهده "),getFont("header"));
                paragraph.setAlignment(Element.ALIGN_CENTER);

                doc.add(paragraph);


                // create a Paragraph object and add the Chunk to it
                /*Paragraph info = new Paragraph(al.process(data), getFont("data"));
                info.setAlignment(Element.ALIGN_RIGHT);
                doc.add(info);*/
                String tasleem = "إستلمت انا ";
                Phrase phrase = new Phrase(al.process(tasleem), getFont("data"));
                phrase.add(new Chunk(Chunk.NEWLINE));
                //al.process
                // ()
                Phrase name = new Phrase(al.process("الاسم /..........................................................."), getFont("data"));
                phrase.add(name);
                phrase.add(new Chunk(Chunk.NEWLINE));
                Phrase wazzefa = new Phrase(al.process("المسمى الوظيفى/ .............................................."),getFont("data"));
                phrase.add(wazzefa);
                phrase.add(new Chunk(Chunk.NEWLINE));
                Phrase nationalId = new Phrase(al.process("رقم قومى /......................................................"),getFont("data"));
                phrase.add(nationalId);
                phrase.add(new Chunk(Chunk.NEWLINE));
                phrase.add(new Chunk(Chunk.NEWLINE));

                Paragraph info = new Paragraph();
                info.setAlignment(Element.ALIGN_RIGHT);
                info.add(phrase);
                doc.add(info);

                PdfPTable table = new PdfPTable(5);

                table.setWidthPercentage(100);

                Stream.of("Product Name","Category","Brand","Serial Number","Mac Address")
                        .forEach(columnTitle->{
                            PdfPCell header = new PdfPCell();
                            //header.setBackgroundColor(BaseColor.GRAY);
                            header.setBorderWidth(2);
                            header.setPhrase(new Phrase(columnTitle));

                            header.setHorizontalAlignment(Element.ALIGN_CENTER);
                            header.setVerticalAlignment(Element.ALIGN_CENTER);
                            table.addCell(header);
                        });
               // PdfPCell cell = new PdfPCell();
               // table.addCell(order.);
                List<Product>products = order.getProducts();
                for (Product product: products) {
                    table.addCell(String.valueOf(product.getProductName()));
                    table.addCell(String.valueOf(product.getCategory().getCategoryName()));
                    table.addCell(String.valueOf(product.getBrand().getBrandName()));
                    table.addCell(String.valueOf(product.getSerialNumber()));
                    table.addCell(String.valueOf(product.getSpecification()));
                }
                doc.add(table);
                Phrase signature = new Phrase( al.process("أقر أنا "),getFont("data"));
                signature.add(new Chunk(Chunk.NEWLINE));

                Phrase depaga = new Phrase(al.process( "الاسم /................................................................ بأننى إستلمت جميع الاصناف اعلاه بحاله جيده وملتزم عند نهايه " +
                        "العقد الموقع بينى وبين شركة الخليج المتحده للاستشارات بإن ارد هذه الاصناف بنفس الحاله التى استلمتها عليها وفى حاله عدم رد العهده الخاضه بى يحق للشركه ان تقوم بخصم قيمتها من مستحقاتى الماليه دون الرجوع الى وإن لم يكن لدى مستحاق لدى الشركه  أتعهد بدفع قيمتها كامله ويحق للشركه حبس جميع مستحقاتى الماليه وجميع الاوراق الخاصه بى لحين سداد القيمه الماليه للعهده "),getFont("data"));
                signature.add(depaga);
                signature.add(new Chunk(Chunk.NEWLINE));
                Phrase finalIqrar =new Phrase(al.process("وهذا إقرار منى بذلك ؛"),getFont("data")) ;
                signature.add(finalIqrar);
                signature.add(new Chunk(Chunk.NEWLINE));
                finalIqrar = new Phrase(al.process("المقر بما فيه ؛"),getFont("data"));
                signature.add(finalIqrar);
                signature.add(new Chunk(Chunk.NEWLINE));
                finalIqrar = new Phrase(al.process("الاسم /................................................"),getFont("data"));
                signature.add(finalIqrar);
                signature.add(new Chunk(Chunk.NEWLINE));
                finalIqrar = new Phrase(al.process("التوقيع /.............................................."),getFont("data"));
                signature.add(finalIqrar);
                signature.add(new Chunk(Chunk.NEWLINE));
                finalIqrar = new Phrase(al.process("تحريرا فى / ........................................"),getFont("data"));
                signature.add(finalIqrar);
                signature.add(new Chunk(Chunk.NEWLINE));


                Paragraph infoSignature = new Paragraph();//al.process(signature),getFont("data")
                infoSignature.setAlignment(Element.ALIGN_RIGHT);
                infoSignature.add(signature);


                infoSignature.setAlignment(Element.ALIGN_RIGHT);
                doc.add(infoSignature);
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

    private Font getFont(String dataFont) throws DocumentException, IOException {
        //Font font = new Font();
        BaseFont bf = BaseFont.createFont("Times_New_Roman.ttf", BaseFont.IDENTITY_H, true);
        switch (dataFont){
            case("header"):
                Font fontHeader = new Font(bf,18,Font.NORMAL, BaseColor.RED);
                fontHeader.setStyle(Font.BOLD);
                //fontHeader.setStyle(PdfPCell.RIGHT);
                return fontHeader;
            case("data"):
               // Font fontData = FontFactory.getFont(,11,BaseColor.BLACK);
                Font fontData = new Font(bf,11,Font.NORMAL,BaseColor.BLACK);
                //fontData.setStyle(Font.NORMAL);
                return fontData;
            default:
                return new Font();

        }

    }
    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("orderId") ;
    }
    public static String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "3ohda-"+time;
    }

}
