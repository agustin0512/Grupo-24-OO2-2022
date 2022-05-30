package com.example.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.entities.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class UserPdfExporter {
	private List<User> listUsers;
	

    public UserPdfExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
    
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Apellido", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Tipo doc", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Numero", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Usuario", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Habilitado", font));
        table.addCell(cell);       
    }
    
    private void writeTableData(PdfPTable table) {
        for (User user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getMail());
            table.addCell(user.getNombre());
            table.addCell(user.getApellido());
            table.addCell(user.getTipodoc());
            table.addCell(String.valueOf(user.getDni()));
            table.addCell(user.getUserRoles().toString());
            table.addCell(String.valueOf(user.isEnabled()));
        }
    }
    
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de usuarios y roles", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.5f, 3.0f, 3.0f, 1.0f, 3.0f, 3.5f, 1.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
