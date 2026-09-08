package io.github.raaviarora.documentqa.document.extractor;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentTextExtractor {

    String extractText(MultipartFile file);
}
