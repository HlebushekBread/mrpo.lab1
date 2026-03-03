package org.mrpo.lab1.services;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

@Service
@PropertySource("classpath:s3.properties")
public class FileService {

    private final S3Template s3Template;

    public FileService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public void uploadFile(String bucketName, String key, InputStream inputStream) {
        // Загрузка файла
        s3Template.upload(bucketName, key, inputStream);
    }

    public S3Resource downloadFile(String bucketName, String key) {
        // Скачивание файла как ресурса Spring
        return s3Template.download(bucketName, key);
    }

    public URL getUrl(String bucketName, String key) {
        // Генерация временной (signed) ссылки на 1 час
        return s3Template.createSignedGetURL(bucketName, key, Duration.ofHours(1));
    }

}
