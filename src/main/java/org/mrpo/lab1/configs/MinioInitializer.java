package org.mrpo.lab1.configs;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MinioInitializer {

    private final MinioClient minioClient;

    public MinioInitializer(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

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
        } catch (Exception e) {
            log.error("Error during MinIO bucket initialization", e);
        }
    }
}