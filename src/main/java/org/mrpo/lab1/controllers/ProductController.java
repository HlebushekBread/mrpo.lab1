package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.dtos.ProductDto;
import org.mrpo.lab1.exceptions.AppException;
import org.mrpo.lab1.models.Product;
import org.mrpo.lab1.services.ImageService;
import org.mrpo.lab1.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{article}")
    public Product getProductByArticle(@PathVariable("article") String article) {
        return productService.findByArticle(article);
    }

    @PostMapping(value = "/update/{article}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @PathVariable("article") String article,
            @RequestPart("product") ProductDto productDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        productService.update(article, productDto);
        if(image!=null) {
            try {
                for(String name : imageService.findFilesByPattern(article)) {
                    imageService.deleteFile(name);
                }
                imageService.uploadFile(image);
            } catch (Exception e) {
                return new ResponseEntity<>(new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity.ok().build();
    }
}
