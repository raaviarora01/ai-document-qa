package io.github.raaviarora.documentqa.document.controller;

import io.github.raaviarora.documentqa.document.dto.DocumentUploadResponse;
import io.github.raaviarora.documentqa.document.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<DocumentUploadResponse> uploadDocument(@RequestParam("file") MultipartFile file){
        DocumentUploadResponse response = documentService.uploadDocument(file);

        return ResponseEntity.ok(response);
    }
}
