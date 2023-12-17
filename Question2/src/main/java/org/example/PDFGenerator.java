package org.example;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class PDFGenerator {
    public static void generatePdf(List<InterviewData> data, String outputPath) throws IOException {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = ChartGenerator.createChart(data);


            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 400; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);

            JFreeChart chart2 = ChartGenerator.generatePieChart(data);

            // Convert JFreeChart to BufferedImage
            int width2 = 600; // set width of the image
            int height2 = 700; // set height of the image
            BufferedImage bufferedImage2 = chart2.createBufferedImage(width2, height2);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage2 = new Image(ImageDataFactory.create(bufferedImage2, null));
            document.add(itextImage2);
        }

    }

}