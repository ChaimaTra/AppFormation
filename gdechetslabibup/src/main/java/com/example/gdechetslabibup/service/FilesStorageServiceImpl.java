package com.example.gdechetslabibup.service;

import java.io.IOException;

import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  @Value("${app.upload-dir}")
  private String uploadDir;

  private Path root; // Root directory for file storage

  // Constructor
  public FilesStorageServiceImpl(@Value("${app.upload-dir}") String uploadDir) {
      this.uploadDir = uploadDir;
      this.root = Paths.get(uploadDir);
      init();
  }
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file, String directory) {
      try {
          Path dirPath = root.resolve(directory);
          Files.createDirectories(dirPath);

          String uniqueFilename = generateUniqueFilename(file);
          Files.copy(file.getInputStream(), dirPath.resolve(uniqueFilename), StandardCopyOption.REPLACE_EXISTING);
          return uniqueFilename;
      } catch (FileAlreadyExistsException e) {
          throw new RuntimeException("A file with the same name already exists.");
      } catch (IOException e) {
          throw new RuntimeException("Failed to save the file: " + e.getMessage());
      }
  }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            URI uri = file.toUri();

            if (uri != null) {
                Resource resource = new UrlResource(uri);
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new IOException("Could not read the file!");
                }
            } else {
                throw new RuntimeException("URI for the file is null!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + e.getMessage());
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path file = root.resolve(filename);
            Files.delete(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the file: " + e.getMessage());
        }
    }

    private String generateUniqueFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        return UUID.randomUUID().toString() + extension;
    }}