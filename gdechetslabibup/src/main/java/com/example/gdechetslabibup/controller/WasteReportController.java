package com.example.gdechetslabibup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.example.gdechetslabibup.racine.racine.root;

import com.example.gdechetslabibup.model.WasteRapportStatus;
import com.example.gdechetslabibup.model.WasteReport;
import com.example.gdechetslabibup.service.WasteReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping(value=root + "/api/wasteReports")
@Tag(name =  "Waste raport", description = "Endpoints for managing Waste raport ")
@PreAuthorize("hasAnyRole('ADMIN','USER')")

public class WasteReportController {
    
    @Autowired
    private WasteReportService wasteReportService;

        @PostMapping
        @Operation(summary = "Create new WasteReport")
        @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('USER')")

    public ResponseEntity<WasteReport> createWasteReport(@RequestBody WasteReport wasteReport) {
        WasteReport createdWasteReport = wasteReportService.createWasteReport(wasteReport);
        return new ResponseEntity<>(createdWasteReport, HttpStatus.CREATED);
    }
        @PostMapping("/upload-image/{id}")
        @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('USER')")

    public ResponseEntity<WasteReport> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        WasteReport updatedWasteReport = wasteReportService.updateWasteRaportImage(id, file);
        return ResponseEntity.ok(updatedWasteReport);
    }
        @PutMapping("/{id}")
        @Operation(summary = "Update existing WasteReport by ID")
        @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('USER')")

    public ResponseEntity<WasteReport> updateWasteReport(@PathVariable Long id, @RequestBody WasteReport wasteReportDetails) {
        WasteReport updatedWasteReport = wasteReportService.updateWasteReport(id, wasteReportDetails);
        return new ResponseEntity<>(updatedWasteReport, HttpStatus.OK);
    }
        @DeleteMapping("/{id}")
        @Operation(summary= "Delete WasteReport by ID")
        @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('USER')")

    public ResponseEntity<Void> deleteWasteReport(@PathVariable Long id) {
        wasteReportService.deleteWasteReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        @GetMapping
        @Operation(summary = "Get all WasteReport")
        @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<List<WasteReport>> getAllWasteReports() {
        List<WasteReport> wasteReports = wasteReportService.getAllWasteReports();
        return new ResponseEntity<>(wasteReports, HttpStatus.OK);
    }
    
    @GetMapping("/file/{filename}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource resource = wasteReportService.loadFile(filename);
        // Implement error handling if resource is not found or cannot be loaded
        return ResponseEntity.ok().body(resource);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get WasteReport by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<WasteReport> getWasteReportById(@PathVariable Long id) {
        WasteReport wasteReport = wasteReportService.getWasteReportById(id)
                .orElseThrow(() -> new RuntimeException("WasteReport not found with id " + id));
        return new ResponseEntity<>(wasteReport, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<WasteReport> updateStatus(@PathVariable Long id, @RequestParam WasteRapportStatus status) {
      WasteReport updatedWasterapport = wasteReportService.updateStatus(id, status)
            .orElseThrow(() -> new RuntimeException("WasteRapport not found with id " + id));
      return new ResponseEntity<>(updatedWasterapport, HttpStatus.OK);
}


@PutMapping("/{id}/category")
@PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('USER')")

   public ResponseEntity<WasteReport> updateCategory(@PathVariable Long id, @RequestParam String category) {
    WasteReport updatedWasteReport = wasteReportService.updateCategory(id, category);
    return new ResponseEntity<>(updatedWasteReport, HttpStatus.OK);
}

@DeleteMapping("/{id}/category")
@PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('USER')")

   public ResponseEntity<WasteReport> deleteCategory(@PathVariable Long id) {
    WasteReport updatedWasteReport = wasteReportService.deleteCategory(id);
    return new ResponseEntity<>(updatedWasteReport, HttpStatus.OK);
}
}
