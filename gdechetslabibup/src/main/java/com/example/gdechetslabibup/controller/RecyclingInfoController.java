package com.example.gdechetslabibup.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.gdechetslabibup.model.RecyclingInfo;
import com.example.gdechetslabibup.service.RecyclingInfoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.example.gdechetslabibup.racine.racine.root;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value=root + "/api/RecyclingInfo")
@Tag(name = "Recycling Info", description = "Endpoints for managing recycling information")
@PreAuthorize("hasAnyRole('ADMIN','USER')")

public class RecyclingInfoController {
    
    @Autowired
    RecyclingInfoService recyclingInfoService;

    @PostMapping
    @Operation(summary = "Create new recycling information")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<RecyclingInfo> createRecycleInfo(@RequestBody RecyclingInfo recyclingInfo ) {
        RecyclingInfo createRecyclingInfo= recyclingInfoService.createRecyclingInfo(recyclingInfo);
        return new ResponseEntity<>(createRecyclingInfo, HttpStatus.CREATED);
    }


        @PostMapping("/upload-media/{id}")
        @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<RecyclingInfo> uploadMedia(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        RecyclingInfo updatedRecyclingInfo = recyclingInfoService.updateRecyclingInfoMedia(id, file);
        return ResponseEntity.ok(updatedRecyclingInfo);
    }

      @PutMapping("/{id}")
      @Operation(summary = "Update existing recycling information by ID")
      @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<RecyclingInfo> updateRecyclingInfo(@PathVariable Long id, @RequestBody RecyclingInfo recyclingInfoDetails) {
        RecyclingInfo updatedRecyclingInfo = recyclingInfoService.updateRecyclingInfo(id, recyclingInfoDetails);
        return new ResponseEntity<>(updatedRecyclingInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recycling information by ID")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<Void> deleteRecyclingInfo(@PathVariable Long id) {
        recyclingInfoService.deleteRecyclingInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    
    @Operation(summary = "Get all recycling information")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<List<RecyclingInfo>> getAllRecyclingInfos() {
        List<RecyclingInfo> recyclingInfos = recyclingInfoService.getAllRecyclingInfos();
        return new ResponseEntity<>(recyclingInfos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recycling information by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<RecyclingInfo> getRecyclingInfoById(@PathVariable Long id) {
        RecyclingInfo recyclingInfo = recyclingInfoService.getRecyclingInfoById(id)
                .orElseThrow(() -> new RuntimeException("RecyclingInfo not found with id " + id));
        return new ResponseEntity<>(recyclingInfo, HttpStatus.OK);
    }
    @GetMapping("/file/{filename}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource resource = recyclingInfoService.loadFile(filename);
        // Implement error handling if resource is not found or cannot be loaded
        return ResponseEntity.ok().body(resource);
}}
