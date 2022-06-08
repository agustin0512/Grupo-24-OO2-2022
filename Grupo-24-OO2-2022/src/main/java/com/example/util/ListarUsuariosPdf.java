package com.example.util;

import java.awt.Color;
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
		
		//FUENTES ,TAMAÑO , COLOR 
		com.lowagie.text.Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Color.DARK_GRAY);
		com.lowagie.text.Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLACK);
		com.lowagie.text.Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		
		//CONFIGURACION DE PAGINA 
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-50, -50, 30, 20);//MARGENES ,IZQ,DER,ARRIBA,ABAJO
		document.open();
		
		/*Tabla Para El Titulo del PDF*/
		
		//CREACION DE TABLA PARA EL TITULO Y CONFIGURACIONES
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda=null;
		celda= new PdfPCell(new Phrase("LISTADO DE USUARIOS",fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(83,171,244));// COLOR DE FONDO TITULO
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(20);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(20);

		@SuppressWarnings("unchecked")
		List<User> usuarios = (List<User>) model.get("usuarios");
		
		//TABLA PARA LOS USUARIOS CON LAS COLUMNAS NECESARIAS
		PdfPTable tablaUsuarios = new PdfPTable(8);
		
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRES", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDOS", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("USUARIO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("TIPO DOC", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);	
		
		celda = new PdfPCell(new Phrase("NUMERO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("ROL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		
		//CARGAMOS LOS DATOS EN LA TABLA
		for (User usuario : usuarios) {
			celda = new PdfPCell(new Phrase(String.valueOf(usuario.getId()), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);

			celda = new PdfPCell(new Phrase(usuario.getNombre(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);

			celda = new PdfPCell(new Phrase(usuario.getApellido(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);

			celda = new PdfPCell(new Phrase(usuario.getMail(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);

			celda = new PdfPCell(new Phrase(usuario.getUsername(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);

			celda = new PdfPCell(new Phrase(usuario.getTipodoc(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(String.valueOf(usuario.getDni()), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			

			celda = new PdfPCell(new Phrase(usuario.getRol().toString(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
		
		}
		document.add(tablaTitulo);
		document.add(tablaUsuarios);				
	}		
}