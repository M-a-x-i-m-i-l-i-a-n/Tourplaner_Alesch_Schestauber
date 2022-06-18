package com.example.javafx.business.ReportGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
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
import javafx.collections.ObservableList;

public class PDFReport {

    public static final String FileDest = "./src/main/resources/Logs/Tour-Report.pdf";

    public static void pdfGenerator(Tour tour, ObservableList<TourLog> logs) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(FileDest));
        Document report = new Document(pdfDocument);
        Table table = new Table(2);
        //public Tour(String name, String description, String from, String to, String type, String time, Double distance, String lrlng, String lrlat, String ullng, String ullat, String mapUrl, String sessionID)
        System.out.println(tour.getUrl());
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        Paragraph a = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph b = new Paragraph(tour.getName())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph c = new Paragraph("Description")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph d = new Paragraph(tour.getDescription())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph e = new Paragraph("Start")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph f = new Paragraph(tour.getStart())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph g = new Paragraph("Destination")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph h = new Paragraph(tour.getDestin())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph i = new Paragraph("Type of travel")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph j = new Paragraph(tour.getType())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph k = new Paragraph("Traveltime")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph l = new Paragraph(tour.getTime())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph m = new Paragraph("Distance")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph n = new Paragraph(String.valueOf(tour.getDistance()))
                .setFont(font).setFontColor(DeviceGray.BLACK);



        Paragraph o = new Paragraph("Map")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Image p = new Image((ImageDataFactory.create(tour.getUrl())));
        //.setNextRenderer(new ImageBackgroundCellRenderer(cell, p));
        //.setHeight(600 * p.getImageHeight() / p.getImageWidth());;
        //TODO: configure map image for table ffs work already u stoopid cont

        paragraphing(table, a, b, c, d, e, f, g);
        paragraphing(table, h, i, j, k, l, m, n);

        table.addCell(new Cell().add(o));
        table.addCell(new Cell().add(p));

        report.add(table);

        report.close();
        System.out.println("Table created successfully...");





    }

    private static void paragraphing(Table table, Paragraph a, Paragraph b, Paragraph c, Paragraph d, Paragraph e, Paragraph f, Paragraph g) {
        table.addCell(new Cell().add(a));
        table.addCell(new Cell().add(b));
        table.addCell(new Cell().add(c));
        table.addCell(new Cell().add(d));
        table.addCell(new Cell().add(e));
        table.addCell(new Cell().add(f));
        table.addCell(new Cell().add(g));

    }


}
