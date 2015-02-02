package com.journeys.pdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfWriter;
import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.util.Validator;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {
 
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private static Font FONT_TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD);
    private static Font FONT_SUBTITLE = new Font(Font.FontFamily.TIMES_ROMAN, 16,
        Font.BOLD);
    private static Font FONT_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 12,
        Font.BOLD);
    private static Font FONT_BLUE = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLUE);
    private static Font FONT_NORMAL = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
        
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        // get data model which is passed by the Spring container
    	Journey journey = (Journey) model.get("journey");
        
    	addMetaData(doc, journey);
    	
    	addJourney(doc, journey);

    }
    
    private static void addMetaData(Document document, Journey journey) {
        document.addTitle(journey.getTitle());
        document.addSubject(journey.getTitle());
        document.addKeywords("Travel, " + journey.getCategoryGeo() + ", " + journey.getCategoryTrip());
        document.addAuthor(journey.getUser().getFullName());
        document.addCreator("My Wonderful Trip");
    }
    
    // ADD ENTITIES
    
    private void addJourney(Document doc, Journey journey) throws DocumentException {
        
        Paragraph journeyParagraph = new Paragraph();
        
        
        // Title
        addEmptyLine(journeyParagraph, 1);
        Paragraph titleParagraph = new Paragraph(journey.getTitle() + " (Du " + sdf.format(journey.getStartDate()) + " au " + sdf.format(journey.getEndDate()) + ")", PDFBuilder.FONT_TITLE);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        journeyParagraph.add(titleParagraph);
        addEmptyLine(journeyParagraph, 2);
        
        try {
			addLink(journeyParagraph, "/app/journey/" + journey.getId(), "Accéder au voyage");
			addEmptyLine(journeyParagraph, 2);
		} catch (MalformedURLException e) {
			// Continue
		}
        
        // Picture
        addImage(journeyParagraph, journey.getPictureUrl());
        addEmptyLine(journeyParagraph, 1);
        
        // write table row data
        for (Day day : journey.getDays()) {
            addDay(journeyParagraph, day);
        }
        
        doc.add(journeyParagraph);
    }
    
    private void addDay(Paragraph paragraph, Day day) {
        
        Paragraph dayParagraph = new Paragraph();
        
        // Title
        addEmptyLine(dayParagraph, 1);
        dayParagraph.add(new Paragraph(sdf.format(day.getDate()) + " : " + day.getTitle(), PDFBuilder.FONT_SUBTITLE));
        addEmptyLine(dayParagraph, 1);
        
        try {
			addLink(dayParagraph, "/app/day/" + day.getId(), "Accéder à la journée");
			addEmptyLine(dayParagraph, 2);
		} catch (MalformedURLException e) {
			// Continue
		}
        
        // Image
        addImage(dayParagraph, day.getPictureUrl());
        addEmptyLine(dayParagraph, 2);
        
        // Content
        addHtmlContent(dayParagraph, day.getContent());
        addEmptyLine(dayParagraph, 1);
        
        paragraph.add(dayParagraph);
    }
    
    // UTILS
    
    private void addImage(Paragraph paragraph, String pictureUrl) {
        
        if (Validator.isNotNull(pictureUrl)) {

            try {
                Image picture = Image.getInstance(pictureUrl);
                picture.scaleToFit(200, 200);
                paragraph.add(picture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
    }
    
    private static void addLink(Paragraph paragraph, String url, String text) throws MalformedURLException {
    	
    	Chunk imdb = new Chunk(text, FONT_BLUE);
        imdb.setAction(new PdfAction(new URL("http://localhost:8080/Journeys" + url)));
        paragraph.add(imdb);
        paragraph.add(".");
    }
    private static void addHtmlContent(Paragraph paragraph, String htmlContent) {
        
    	if (Validator.isNotNull(htmlContent)) {
    		String textContent = Jsoup.parse(htmlContent).text();
            paragraph.add(new Paragraph(textContent));
    	}
    	
    }
 
}