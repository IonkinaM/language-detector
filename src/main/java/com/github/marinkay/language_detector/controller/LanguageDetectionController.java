package com.github.marinkay.language_detector.controller;

import com.github.marinkay.language_detector.service.OpenNLPLanguageDetector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/language")
public class LanguageDetectionController {

    private final OpenNLPLanguageDetector detector;

    public LanguageDetectionController(OpenNLPLanguageDetector detector) {
        this.detector = detector;
    }

    @PostMapping("/detect")
    public String detect(@RequestBody String text) {
        return detector.detectLanguage(text);
    }
    @PostMapping(value = "/detect-file", consumes = "multipart/form-data")
    public ResponseEntity<String> detectLanguageFromFile(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String language = detector.detectLanguageFromFile(file);
            return ResponseEntity.ok(language);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}
