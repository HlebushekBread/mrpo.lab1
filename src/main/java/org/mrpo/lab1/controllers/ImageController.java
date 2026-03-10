package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    /*
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("image") MultipartFile file) {
        try {
            imageService.uploadFile(file);
            return ResponseEntity.ok(Map.of(
                    "message", "Файл успешно загружен: " + file.getOriginalFilename()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Ошибка при загрузке: " + e.getMessage()
            ));
        }
    }
    */

    @GetMapping("/link/{filename}")
    public ResponseEntity<Map<String, String>> getLink(@PathVariable String filename) {
        try {
            String url = imageService.getFileLink(filename);
            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Ошибка при генерации ссылки: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/delete/{filename}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String filename) {
        try {
            imageService.deleteFile(filename);
            return ResponseEntity.ok(Map.of(
                    "message", "Файл '" + filename + "' успешно удален"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Ошибка при удалении: " + e.getMessage()
            ));
        }
    }
}

