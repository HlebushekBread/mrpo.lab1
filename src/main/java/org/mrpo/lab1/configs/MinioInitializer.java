package org.mrpo.lab1.configs;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
@Component
public class MinioInitializer {

    private final MinioClient minioClient;
    private final ResourcePatternResolver resourceResolver;

    @Value("${s3.bucket}")
    private String bucketName;

    private static final Logger log = LoggerFactory.getLogger(MinioInitializer.class);
    
    @EventListener(ApplicationReadyEvent.class)
    public void initBuckets() {
        try {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );

            if (!found) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
                log.info("Bucket '{}' successfully created.", bucketName);
            } else {
                log.info("Bucket '{}' already exists.", bucketName);
            }

            Resource[] resources = resourceResolver.getResources("classpath:init-data/minio/*");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                String objectName = (filename != null && filename.contains("."))
                        ? filename.substring(0, filename.lastIndexOf('.')) : filename;

                try (InputStream is = resource.getInputStream()) {
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(objectName)
                                    .stream(is, resource.contentLength(), -1)
                                    .contentType(Files.probeContentType(Path.of(filename)))
                                    .build()
                    );
                    log.info("Uploaded init-data image: " + filename + " as " + objectName);
                }
            }
        } catch (Exception e) {
            log.error("Error during MinIO initialization", e);
        }
    }
}