package com.example.javafx.business.ReportGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.javafx.model.Tour;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class PDFReport {

    public static final String FileDest = "Tour-Report.pdf";

    public static void pdfGenerator(Tour tour) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(FileDest));
        Document report = new Document(pdfDocument);
        Table table = new Table(2);
        //public Tour(String name, String description, String from, String to, String type, String time, Double distance, String lrlng, String lrlat, String ullng, String ullat, String mapUrl, String sessionID)



        Cell cell = new Cell();
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Paragraph p = new Paragraph("A cell with an image as background color.")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        cell.add(p);


        Paragraph a = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph b = new Paragraph(tour.getName())
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph c = new Paragraph("Description")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph d = new Paragraph(tour.getDescription())
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph e = new Paragraph("Start")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph f = new Paragraph(tour.getStart())
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph g = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph h = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph i = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph j = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);
        Paragraph k = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.WHITE);

        table.addCell(new Cell().add(a)
                .setFont(font).setFontColor(DeviceGray.WHITE));
        /*table.addCell(new Cell().add(tour.getName()));
        table.addCell(new Cell().add("Description"));
        table.addCell(new Cell().add(tour.getDescription()));
        table.addCell(new Cell().add("Start"));
        table.addCell(new Cell().add(tour.getStart()));
        table.addCell(new Cell().add("Destination"));
        table.addCell(new Cell().add(tour.getDestin()));
        table.addCell(new Cell().add("Tyope of travel"));
        table.addCell(new Cell().add(tour.getType()));
        table.addCell(new Cell().add("Traveltime"));
        table.addCell(new Cell().add(tour.getTime()));
        table.addCell(new Cell().add("Distance"));
        table.addCell(new Cell().add(tour.getDistance()));
        table.addCell(new Cell().add(""));
        table.addCell(new Cell().add(""));


         */


    }






}
