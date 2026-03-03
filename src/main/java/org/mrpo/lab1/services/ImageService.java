package org.mrpo.lab1.services;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final MinioClient minioClient;

    public ImageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${s3.bucket}")
    private String bucket;

    public void uploadFile(MultipartFile file) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
    }

    public String getFileLink(String filename) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucket)
                        .object(filename)
                        .expiry(2, java.util.concurrent.TimeUnit.HOURS)
                        .build()
        );
    }
}