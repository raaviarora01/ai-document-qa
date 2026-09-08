package io.github.raaviarora.document_qa.document.service;

import io.github.raaviarora.document_qa.document.dto.DocumentUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

    public DocumentUploadResponse uploadDocument(MultipartFile file){
        validateFile(file);

        return new DocumentUploadResponse(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType(),
                "Document uploaded successfullly"
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
