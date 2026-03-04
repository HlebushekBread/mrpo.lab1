package org.mrpo.lab1.services;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final MinioClient minioClient;

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

    public void deleteFile(String filename) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(filename)
                        .build()
        );
    }

    public List<String> findFilesByPattern(String filename) throws Exception {
        List<String> foundFiles = new ArrayList<>();

        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucket)
                        .prefix(filename + ".")
                        .build()
        );

        for (Result<Item> result : results) {
            Item item = result.get();
            String name = item.objectName();

            if (name.matches("^" + Pattern.quote(filename) + "\\.[a-zA-Z0-9]+$")) {
                foundFiles.add(name);
            }
        }

        return foundFiles;
    }
}