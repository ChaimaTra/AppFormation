package com.example.gdechetslabibup.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService{

  void init();
  String save(MultipartFile file, String directory);
  Resource load(String filename);
  void delete(String filename);

}
