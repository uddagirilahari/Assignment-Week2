package org.example;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;

public class PDFGenerator {

    public static void generatePdf(String pdfFileName) throws IOException {
        try (PdfDocument pdf = new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter(new FileOutputStream(pdfFileName)));
             Document document = new Document(pdf)) {

            Image pieChart = new Image(ImageDataFactory.create("pie_chart.png"));
            document.add(pieChart);

            document.add(new com.itextpdf.layout.element.Paragraph("\n"));

            Image barChart = new Image(ImageDataFactory.create("bar_chart.png"));
            document.add(barChart);

            document.close();
        }
    }

}

