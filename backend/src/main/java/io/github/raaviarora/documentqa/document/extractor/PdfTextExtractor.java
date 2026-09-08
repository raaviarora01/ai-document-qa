package io.github.raaviarora.documentqa.document.extractor;

import io.github.raaviarora.documentqa.document.exception.InvalidDocumentException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class PdfTextExtractor implements DocumentTextExtractor{

    @Override
    public String extractText(MultipartFile file) {
        try(PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper textStripper = new PDFTextStripper();

            String extractedText = textStripper.getText(document);

            if(extractedText == null || extractedText.isBlank()){
                throw new InvalidDocumentException("PDF does not contain extractable text");
            }

            return extractedText;
        } catch (IOException e) {
            throw new InvalidDocumentException("Failed to extract text from PDF", e);
        }
    }
}
