/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

import cinema.logic.Factura;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.*;
import java.util.ArrayList;


public class pdfPrinter {
     // public static final String DEST = "HistorialAcademico.pdf";
  
 /* public static void main(String args[]) throws IOException {
    createPdf(DEST);
  }*/
  //Ahora se genera numeros de factura con Strings
   public void createPdf(String dest, Factura u, TandaJS t) throws IOException, Exception {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        ImageData data = ImageDataFactory.create("C:/Users/David/Documents/NetBeansProjects/Proyecto2Progra4/Proyecto2-Progra4/web/Img/tidael3.jpeg");        
        Image img = new Image(data); 
        document.add(img);
        document.add(new Paragraph(""));
        document.add(new Paragraph("Tidael's Cinema" ).setFont(font).setBold().setFontSize(20f));
        document.add(new Paragraph("Factura#" + u.getId()).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String titleCerf = "Cliente: " + u.getNameCliente();
        document.add(new Paragraph(titleCerf).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String idCliente = "ID del Cliente: " + u.getIdCliente();
        document.add(new Paragraph(idCliente).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String peli = "Pelicula: " + t.getPelicula();
        document.add(new Paragraph(peli).setFont(font).setBold().setFontSize(20f));
               
        document.add(new Paragraph(""));
        String tanda = "Fecha: " + t.getHora();
        document.add(new Paragraph(tanda).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String sala = "Sala: " + t.getIdSala();
        document.add(new Paragraph(sala).setFont(font).setBold().setFontSize(20f));
        
       
        
        Table table = new Table(2);
        Cell c; 
        Color bkg = ColorConstants.BLUE;
        Color frg= ColorConstants.WHITE;
        c= new Cell(); c.add(new Paragraph("Butacas")).setBackgroundColor(bkg).setFontColor(frg); 
        table.addHeaderCell(c);
        c= new Cell(); c.add(new Paragraph("Precio")).setBackgroundColor(bkg).setFontColor(frg);
        table.addHeaderCell(c);     
        /*c= new Cell(); c.add(new Paragraph("Nota")).setBackgroundColor(bkg).setFontColor(frg);
        table.addHeaderCell(c);    */               
        ArrayList<String> arrRecord = u.getButacas();
        
        if (arrRecord == null) {
           Exception ee = new Exception();
           throw ee;
       }
        String butaca;
        String v = "₡"+String.valueOf(t.getPrecio());

        int tam = arrRecord.size();
        //---------
        for(String r: arrRecord){
            butaca = r;
                       
            //Se agrega los valores obtenidos al pdf
            table.addHeaderCell(butaca);
            table.addHeaderCell(v);                   
        }
        document.add(table);
        
        
        document.add(new Paragraph(""));
        String total = "Total: ₡" + String.valueOf(t.getPrecio() * tam);
        document.add(new Paragraph(total).setFont(font).setBold().setFontSize(20f));
        document.close();
    }
   
   //--------------------------------------------------------------------------------------------------------
   
   public void createPdfTiquetes(String dest, Factura u, TandaJS t) throws IOException, Exception {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        ImageData data = ImageDataFactory.create("C:/Users/David/Documents/NetBeansProjects/Proyecto2Progra4/Proyecto2-Progra4/web/Img/movieTicket.jpg");        
        Image img = new Image(data); 
        document.add(img);
        document.add(new Paragraph(""));
        document.add(new Paragraph("Tiquete Pelicula Tidael's Cinema" ).setFont(font).setBold().setFontSize(20f));
                     
        document.add(new Paragraph(""));
        String peli = "Pelicula: " + t.getPelicula();
        document.add(new Paragraph(peli).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String tanda = "Tanda: " + t.getIdTanda();
        document.add(new Paragraph(tanda).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String sala = "Sala: " + t.getIdSala();
        document.add(new Paragraph(sala).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String fecha = "Hora: " + t.getFecha();
        document.add(new Paragraph(fecha).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String hora = "Fecha: " + t.getHora();
        document.add(new Paragraph(hora).setFont(font).setBold().setFontSize(20f));
        
        document.add(new Paragraph(""));
        String asiento = "Asiento: " + u.getButacas().get(0);
        document.add(new Paragraph(asiento).setFont(font).setBold().setFontSize(20f));
        
      
        document.close();
    }
    
}