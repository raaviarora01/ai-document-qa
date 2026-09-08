package io.github.raaviarora.document_qa.document.dto;

public record DocumentUploadResponse(
        String fileName,
        long fileSize,
        String contentType,
        String message
) {
}
