package io.github.raaviarora.documentqa.document.service;

import io.github.raaviarora.documentqa.document.dto.DocumentUploadResponse;
import io.github.raaviarora.documentqa.document.extractor.DocumentTextExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

    private final DocumentTextExtractor documentTextExtractor;

    public DocumentService(DocumentTextExtractor documentTextExtractor){
        this.documentTextExtractor = documentTextExtractor;
    }

    public DocumentUploadResponse uploadDocument(MultipartFile file){
        validateFile(file);

        String extractedText = documentTextExtractor.extractText(file);

        return new DocumentUploadResponse(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType(),
                extractedText.length(),
                "Document uploaded successfully"
        );
    }

    private void validateFile(MultipartFile file) {
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("File must not be empty");
        }

        if(!"application/pdf".equalsIgnoreCase(file.getContentType())){
            throw new IllegalArgumentException("Only pdf files are supported");
        }
    }
}
