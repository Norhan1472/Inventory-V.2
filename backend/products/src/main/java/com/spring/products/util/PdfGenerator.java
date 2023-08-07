package com.spring.products.util;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.products.entity.History;

public class PdfGenerator {
    public void generate(List <History> historyList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("List of the History", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(9);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3,3,3,3,3,3,3});
        table.setSpacingBefore(5);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        // Adding headings in the created table cell or  header
        // Adding Cell to table
        cell.setPhrase(new Phrase("History ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Employee Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Product Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Serial Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Product Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Brand", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date Received", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date Retrieved", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Reason", font));
        table.addCell(cell);
        // Iterating the list of histories
        for (History history: historyList) {
            // Adding History id
            table.addCell(String.valueOf(history.getHistoryId()));
            // Adding employee name
            table.addCell(history.getEmpName());
            // Adding product name
            table.addCell(history.getProductName());
            // Adding product serial number
            table.addCell(history.getSerialNumber());
            // Adding product description
            table.addCell(history.getProductDesc());
            // Adding brand
            table.addCell(history.getBrand());
            // Adding date receive product
            table.addCell(String.valueOf(history.getDateReceived()));
            // Adding date return product
            table.addCell(String.valueOf(history.getDateRetrieved()));
            // Adding reason
            table.addCell(history.getReason());
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }
}
