package io.github.raaviarora.documentqa.document.dto;

public record DocumentUploadResponse(
        String fileName,
        long fileSize,
        String contentType,
        int extractedCharacters,
        String message
) {
}
