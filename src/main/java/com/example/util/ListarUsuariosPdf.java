package com.example.util;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.entities.User;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/views/usuarios/listar")
public class ListarUsuariosPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		com.lowagie.text.Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Color.BLUE);
		com.lowagie.text.Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLUE);
		com.lowagie.text.Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		
		
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();
		
		/*Tabla Para El Titulo del PDF*/
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda=null;
		
		
		
		//Font fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.BLUE);
		
		celda= new PdfPCell(new Phrase("LISTADO DE USUARIOS",fuenteTitulo));
		
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40,90,120));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);
		
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		PdfPCell cell = new PdfPCell();
		PdfPTable tablaPColumna = new PdfPTable(7);
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
         
        com.lowagie.text.Font fuenteColumnas = FontFactory.getFont(FontFactory.HELVETICA);
        fuenteColumnas.setColor(Color.BLACK);
        fuenteColumnas.setStyle(Font.BOLD);
        fuenteColumnas.setStyle(Font.CENTER_BASELINE);
         
        cell.setPhrase(new Phrase("Nombre", fuenteColumnas));
        tablaPColumna.addCell(cell);
        
        cell.setPhrase(new Phrase("Apellido", fuenteColumnas));
        tablaPColumna.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", fuenteColumnas));
        tablaPColumna.addCell(cell);
        
        cell.setPhrase(new Phrase("Usuario", fuenteColumnas));
        tablaPColumna.addCell(cell);
         
        cell.setPhrase(new Phrase("Tipo doc", fuenteColumnas));
        tablaPColumna.addCell(cell);
        
        cell.setPhrase(new Phrase("Numero", fuenteColumnas));
        tablaPColumna.addCell(cell);
        
        cell.setPhrase(new Phrase("Roles", fuenteColumnas));
        tablaPColumna.addCell(cell);

		
		PdfPTable tablaUsuarios = new PdfPTable(7);
		@SuppressWarnings("unchecked")
		List<User> usuarios = (List<User>) model.get("usuarios");
		
		
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRES", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDOS", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);
		
		celda = new PdfPCell(new Phrase("TELEFONO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);
		
		celda = new PdfPCell(new Phrase("CIUDAD", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaTitulo.addCell(celda);		
		
		
		
		usuarios.forEach(usuario ->{
			tablaUsuarios.addCell(usuario.getNombre());
			tablaUsuarios.addCell(usuario.getApellido());
			tablaUsuarios.addCell(usuario.getMail());
			tablaUsuarios.addCell(usuario.getUsername());
			tablaUsuarios.addCell(usuario.getTipodoc());
			tablaUsuarios.addCell(String.valueOf(usuario.getDni()));
			

		});
		document.add(tablaTitulo);
		document.add(tablaPColumna);
		document.add(tablaUsuarios);
		
		
	}
	
	

}
