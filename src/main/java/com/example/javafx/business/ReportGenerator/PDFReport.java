package com.example.javafx.business.ReportGenerator;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

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
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import javafx.collections.ObservableList;


public class PDFReport {

    public static final String FileDest = "./src/main/resources/Logs/Tour-Report.pdf";
    PdfFont font;
    public PDFReport(){
        try{
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pdfGenerator(Tour tour, ObservableList<TourLog> logs) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(FileDest));
        Document report = new Document(pdfDocument);

        Table tourTable = new Table(2);
        Table logsTable = new Table(2);


        Paragraph a = new Paragraph("Tourname")
                .setFont(font).setFontColor(DeviceGray.BLACK);
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

        String destinationFile = "mapPic.jpg";

        paragraphingTour(tourTable, a, b, c, d, q, f, g);
        paragraphingTour(tourTable, h, i, j, k, l, m, n);

        try{
            URL url = new URL(tour.getUrl());
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] r = new byte[2048];
            int length;

            while ((length = is.read(r)) != -1) {
                os.write(r, 0, length);
            }

            is.close();
            os.close();
            tourTable.addCell(new Cell().add(o));

            Image img = new Image(ImageDataFactory.create(destinationFile));
            Cell cell = new Cell();
            cell.setNextRenderer(new ImageBackgroundCellRenderer(cell,img));
            cell.setHeight(img.getImageHeight()/2);
            cell.setWidth(img.getImageWidth()/2);
            tourTable.addCell(cell);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        makeLogsTable(logs, logsTable);

        report.add(tourTable);

        report.close();
        System.out.println("Table created successfully...");

    }

    private void paragraphingTour(Table table, Paragraph a, Paragraph b, Paragraph c, Paragraph d, Paragraph e, Paragraph f, Paragraph g) {
        table.addCell(new Cell().add(a));
        table.addCell(new Cell().add(b));
        table.addCell(new Cell().add(c));
        table.addCell(new Cell().add(d));
        table.addCell(new Cell().add(e));
        table.addCell(new Cell().add(f));
        table.addCell(new Cell().add(g));

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
                    .setFont(font).setFontColor(DeviceGray.BLACK);
            Paragraph b = new Paragraph(log.getDate())
                    .setFont(font).setFontColor(DeviceGray.BLACK);
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
}
