package com.example.javafx.business.ReportGenerator;

import java.io.*;
import java.net.URL;


import com.example.javafx.DataAccessLayer.LogDAO;
import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PDFReport {


    public static final String StatDest = "file:./Files/Reports/Tours-Stats-Report.pdf";
    private static Logger logger = LogManager.getLogger();
    PdfFont font;
    public PDFReport(){
        try{
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug(e);
        }

    }

    public void pdfTourGenerator(Tour tour, ObservableList<TourLog> logs) throws IOException {
        final String FileDest = "./Files/Reports/" + tour.getName() + ".pdf";
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(FileDest));
        Document report = new Document(pdfDocument);

        Table tourTable = new Table(2);
        Table logsTable = new Table(2);
        tourTable.setMarginBottom(25);



        Paragraph a = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.BLACK)
                .setWidth(150);
        Paragraph b = new Paragraph(tour.getName())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph c = new Paragraph("Description")
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph d = new Paragraph(tour.getDescription())
                .setFont(font).setFontColor(DeviceGray.BLACK);
        Paragraph q = new Paragraph("Start")
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



        paragraphingTour(tourTable, a, b, c, d, q, f, g);
        paragraphingTour(tourTable, h, i, j, k, l, m, n);

        try{

        tourTable.addCell(new Cell().add(o));

            Image img = new Image(ImageDataFactory.create("file:./Files/images/" + tour.getName() + ".jpg"));
            Cell cell = new Cell();
            cell.setNextRenderer(new ImageBackgroundCellRenderer(cell,img));
            cell.setHeight(img.getImageHeight()/4);
            cell.setWidth(img.getImageWidth()/4);
            tourTable.addCell(cell);


        }catch (IOException e)
        {
            e.printStackTrace();
            logger.debug(e);
        }

        makeLogsTable(logs, logsTable);

        report.add(tourTable);
        report.add(logsTable);

        report.close();
        System.out.println("Table created successfully...");
        logger.info("Table created successfully...");

    }

    private void paragraphingTour(Table tourTable, Paragraph a, Paragraph b, Paragraph c, Paragraph d, Paragraph e, Paragraph f, Paragraph g) {
        tourTable.addCell(new Cell().add(a));
        tourTable.addCell(new Cell().add(b));
        tourTable.addCell(new Cell().add(c));
        tourTable.addCell(new Cell().add(d));
        tourTable.addCell(new Cell().add(e));
        tourTable.addCell(new Cell().add(f));
        tourTable.addCell(new Cell().add(g));

    }

    private static class ImageBackgroundCellRenderer extends CellRenderer {
        protected Image img;

        public ImageBackgroundCellRenderer(Cell modelElement, Image img) {
            super(modelElement);
            this.img = img;
        }

        // If a renderer overflows on the next area, iText uses #getNextRenderer() method to create a new renderer for the overflow part.
        // If #getNextRenderer() isn't overridden, the default method will be used and thus the default rather than the custom
        // renderer will be created
        @Override
        public IRenderer getNextRenderer() {
            return new ImageBackgroundCellRenderer((Cell) modelElement, img);
        }

        @Override
        public void draw(DrawContext drawContext) {
            img.scaleToFit(getOccupiedAreaBBox().getWidth(), getOccupiedAreaBBox().getHeight());
            drawContext.getCanvas().addXObjectFittedIntoRectangle(img.getXObject(), getOccupiedAreaBBox());
            super.draw(drawContext);
        }
    }

    private void makeLogsTable(ObservableList<TourLog> logs, Table logsTable){
        for (TourLog log: logs) {
            Paragraph a = new Paragraph("Date")
                    .setFont(font).setFontColor(DeviceGray.BLACK)
                    .setWidth(150);
            Paragraph b = new Paragraph(log.getDate())
                    .setFont(font).setFontColor(DeviceGray.BLACK)
                    .setWidth(365);
            Paragraph c = new Paragraph("Time")
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph d = new Paragraph(log.getTime())
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph e = new Paragraph("Total Time")
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph f = new Paragraph(String.valueOf(log.getTotalTime()))
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph g = new Paragraph("difficulty")
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph h = new Paragraph(String.valueOf(log.getDifficulty()))
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph i = new Paragraph("Rating")
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph j = new Paragraph(String.valueOf(log.getRating()))
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph k = new Paragraph("comment")
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph l = new Paragraph(log.getComment())
                    .setFont(font).setFontColor(DeviceGray.BLACK);

            paragraphingLogs(logsTable,a,b,c,d,e,f);
            paragraphingLogs(logsTable,g,h,i,j,k,l);
            logsTable.addCell(new Cell().setBackgroundColor(DeviceGray.BLACK).setHeight(2));
            logsTable.addCell(new Cell().setBackgroundColor(DeviceGray.BLACK).setHeight(2));


        }

    }

    private void paragraphingLogs(Table logsTable, Paragraph a, Paragraph b, Paragraph c, Paragraph d, Paragraph e, Paragraph f){
        logsTable.addCell(new Cell().add(a));
        logsTable.addCell(new Cell().add(b));
        logsTable.addCell(new Cell().add(c));
        logsTable.addCell(new Cell().add(d));
        logsTable.addCell(new Cell().add(e));
        logsTable.addCell(new Cell().add(f));
    }

    public void pdfStatGenerator(ObservableList<Tour> tours) throws FileNotFoundException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(StatDest));
        Document report = new Document(pdfDocument);
        int rating = 0;
        int completinoTime = 0;

        Table tourStatTable = new Table(2)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setWidth(300);
        LogDAO logDAO = LogDAO.getInstance();
        float tourlogamount = logDAO.getTourLogCount();

        for(Tour tour : tours){
            String tourname = tour.getName();

            ObservableList<TourLog> logs = logDAO.getTourLogsByTourname(tourname);
            for(TourLog log : logs){
                rating += log.getRating();
                completinoTime += log.getTotalTime();

            }

            Paragraph a = new Paragraph(tour.getName())
                    .setFont(font).setFontSize(20).setFontColor(DeviceRgb.BLUE)
                    .setWidth(100)
                    .setBold();

            Paragraph b = new Paragraph("Average Rating");
            Paragraph c = new Paragraph(String.valueOf(rating/tourlogamount));
            Paragraph d = new Paragraph("Average Time needed");
            Paragraph e = new Paragraph(String.valueOf(completinoTime/tourlogamount) + " hours");
            Paragraph f = new Paragraph(" ");
            Paragraph g = new Paragraph("Distance");
            Paragraph h = new Paragraph(String.valueOf(tour.getDistance()) + " km");


            tourStatTable.addCell(new Cell().add(a));
            tourStatTable.addCell(new Cell().add(f));
            tourStatTable.addCell(new Cell().add(g));
            tourStatTable.addCell(new Cell().add(h));
            tourStatTable.addCell(new Cell().add(b));
            tourStatTable.addCell(new Cell().add(c));
            tourStatTable.addCell(new Cell().add(d));
            tourStatTable.addCell(new Cell().add(e));
            tourStatTable.addCell(new Cell().setBackgroundColor(DeviceGray.BLACK).setHeight(2)).setMarginBottom(20);
            tourStatTable.addCell(new Cell().setBackgroundColor(DeviceGray.BLACK).setHeight(2)).setMarginBottom(20);
        }

        report.add(tourStatTable);
        report.close();
        System.out.println("Stats file created!");
    }
}
