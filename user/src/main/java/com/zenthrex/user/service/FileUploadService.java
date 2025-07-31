package com.zenthrex.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadService {

    @Value("${file.upload.dir:uploads/avatars}")
    private String uploadDir;

    @Value("${file.base.url:http://localhost:8080/uploads/avatars}")
    private String baseUrl;

    public String uploadAvatar(MultipartFile file, Integer userId) {
        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String filename = "avatar_" + userId + "_" + UUID.randomUUID() + extension;

            // Save file
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            log.info("Avatar uploaded successfully for user {}: {}", userId, filename);

            // Return URL
            return baseUrl + "/" + filename;

        } catch (IOException e) {
            log.error("Failed to upload avatar for user {}", userId, e);
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public void deleteAvatar(Integer userId) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            // Find and delete all avatar files for this user
            Files.walk(uploadPath)
                    .filter(path -> path.getFileName().toString().startsWith("avatar_" + userId + "_"))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                            log.info("Deleted avatar file: {}", path.getFileName());
                        } catch (IOException e) {
                            log.error("Failed to delete avatar file: {}", path.getFileName(), e);
                        }
                    });

        } catch (IOException e) {
            log.error("Failed to delete avatar for user {}", userId, e);
            throw new RuntimeException("Failed to delete avatar", e);
        }
    }
}