package com.example.gdechetslabibup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.gdechetslabibup.model.WasteRapportStatus;
import com.example.gdechetslabibup.model.WasteReport;
import com.example.gdechetslabibup.repos.WasteReportRepository;

import org.springframework.core.io.Resource;

import com.example.gdechetslabibup.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WasteReportService {

    @Autowired
    private WasteReportRepository wasteReportRepository;

    @Autowired
    private FilesStorageService filesStorageService;

    private static final String WASTE_REPORT_DIRECTORY = "waste-reports";


    public WasteReport createWasteReport(WasteReport wasteReport) {
          
        wasteReport.setCreatedAt(LocalDateTime.now());
        
        wasteReport.setStatus(WasteRapportStatus.non_effectue);

        return wasteReportRepository.save(wasteReport);
    }

    public WasteReport updateWasteReport(Long reportId, WasteReport reportDetails) {
        WasteReport wasteReport = wasteReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Waste report not found with id: " + reportId));

        wasteReport.setDescription(reportDetails.getDescription());
        wasteReport.setPhotoUrl(reportDetails.getPhotoUrl());
        

        return wasteReportRepository.save(wasteReport);
    }
    public Resource loadFile(String filename) {
       
        return filesStorageService.load(filename);
    }
    /*public void deleteWasteReport(Long reportId) {
        wasteReportRepository.deleteById(reportId);
    }

    public WasteReport getWasteReportById(Long reportId) {
        return wasteReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Waste report not found with id: " + reportId));
    }

    public List<WasteReport> getAllWasteReports() {
        return wasteReportRepository.findAll();
    }*/
    public void deleteWasteReport(Long id) {
        WasteReport wasteReport = wasteReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WasteReport not found with id " + id));
        wasteReportRepository.delete(wasteReport);
    }

    public List<WasteReport> getAllWasteReports() {
        return wasteReportRepository.findAll();
    }

    public Optional<WasteReport> getWasteReportById(Long id) {
        return wasteReportRepository.findById(id);
    }

    public Optional<WasteReport> updateStatus(Long id, WasteRapportStatus status) {
        Optional<WasteReport> optionalWasterapport = wasteReportRepository.findById(id);
        if (optionalWasterapport.isPresent()) {
            WasteReport wasterapport = optionalWasterapport.get();
            wasterapport.setStatus(status);
            wasteReportRepository.save(wasterapport);
            return Optional.of(wasterapport);
        } else {
            return Optional.empty();
        }
    }

    public WasteReport updateCategory(Long id, String category) {
        WasteReport wasteReport = wasteReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WasteReport not found with id " + id));
        wasteReport.setCategory(category);
        return wasteReportRepository.save(wasteReport);
    }
    public WasteReport deleteCategory(Long id) {
        WasteReport wasteReport = wasteReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WasteReport not found with id " + id));
        wasteReport.setCategory(null);
        return wasteReportRepository.save(wasteReport);
    }
    public WasteReport updateWasteRaportImage(Long id, MultipartFile file) {
        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
      
        // Retrieve the contact by ID, throw an EntityNotFoundException if the contact
        // is not found
        WasteReport wasteReport = wasteReportRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("WasteReport not found with id " + id));
         
        String filename = filesStorageService.save(file, WASTE_REPORT_DIRECTORY);

        // Check if the contact already has an image
        if (wasteReport.getPhotoUrl() != null) {
            filesStorageService.delete(wasteReport.getPhotoUrl());
        }

        wasteReport.setPhotoUrl(filename);
        return wasteReportRepository.save(wasteReport);
    }
}
