package com.github.marinkay.language_detector.service;

import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class OpenNLPLanguageDetector {

    private final LanguageDetectorME detector;

    public OpenNLPLanguageDetector() throws IOException {
        // Загрузка модели (указывайте свой путь к файлу)
        LanguageDetectorModel model = new LanguageDetectorModel(
                new File("src/main/resources/langdetect-183.bin")
        );
        this.detector = new LanguageDetectorME(model);
    }

    public String detectLanguage(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "UNKNOWN";
        }
        return detector.predictLanguage(text).getLang();
    }
    public String detectLanguageFromFile(MultipartFile file) throws IOException {
        String text = new String(file.getBytes(), StandardCharsets.UTF_8);
        return detectLanguage(text);
    }
}
