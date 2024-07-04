package com.example.gdechetslabibup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.gdechetslabibup.model.RecyclingInfo;
import com.example.gdechetslabibup.model.WasteReport;
//import com.example.gdechetslabibup.dto.RecyclingInfo;
import com.example.gdechetslabibup.repos.RecyclingInfoRepository;
import com.example.gdechetslabibup.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingInfoService {

    @Autowired
    private RecyclingInfoRepository recyclingInfoRepository;

    @Autowired
    private FilesStorageService filesStorageService;

    private static final String RECYCLING_INFO_DIRECTORY = "recycling-info";


    public RecyclingInfo createRecyclingInfo(RecyclingInfo recyclingInfo) {
        return recyclingInfoRepository.save(recyclingInfo);
    }

    public RecyclingInfo updateRecyclingInfo(Long infoId, RecyclingInfo infoDetails) {
        RecyclingInfo recyclingInfo = recyclingInfoRepository.findById(infoId)
                .orElseThrow(() -> new EntityNotFoundException("Recycling info not found with id: " + infoId));

        recyclingInfo.setTitle(infoDetails.getTitle());
        recyclingInfo.setContent(infoDetails.getContent());
        recyclingInfo.setMediaUrl(infoDetails.getMediaUrl());
        

        return recyclingInfoRepository.save(recyclingInfo);
    }

    public void deleteRecyclingInfo(Long infoId) {
        recyclingInfoRepository.deleteById(infoId);
    }

   /*  public RecyclingInfo getRecyclingInfoById(Long infoId) {
        return recyclingInfoRepository.findById(infoId)
                .orElseThrow(() -> new EntityNotFoundException("Recycling info not found with id: " + infoId));
    }*/

    public List<RecyclingInfo> getAllRecyclingInfos() {
        return recyclingInfoRepository.findAll();
    }
    public Optional<RecyclingInfo> getRecyclingInfoById(Long id) {
        return recyclingInfoRepository.findById(id);
    }

  public RecyclingInfo updateRecyclingInfoMedia(Long id, MultipartFile file) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        RecyclingInfo recyclingInfo = recyclingInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecyclingInfo not found with id " + id));

        String filename = filesStorageService.save(file, RECYCLING_INFO_DIRECTORY);

        if (recyclingInfo.getMediaUrl() != null) {
            filesStorageService.delete(recyclingInfo.getMediaUrl());
        }

        recyclingInfo.setMediaUrl(filename);
        return recyclingInfoRepository.save(recyclingInfo);
    }
    public Resource loadFile(String filename) {
        
        return filesStorageService.load(filename);
    }
}
