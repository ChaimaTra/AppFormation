package com.example.gdechetslabibup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.gdechetslabibup.model.Information;
import com.example.gdechetslabibup.service.InformationService;

@RestController
@RequestMapping("/informations")
@PreAuthorize("hasAnyRole('USER','ADMIN')") // Autorisation requise pour les utilisateurs

public class InformationController {

    @Autowired
    private InformationService informationService;

    @PostMapping("/{senderUserId}/{recipientUserId}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('USER')") // Autorisation requise pour écrire une information

    public ResponseEntity<Information> sendInformation(@PathVariable Long senderUserId, @PathVariable Long recipientUserId, @RequestBody Information information) {
        Information sentInformation = informationService.sendInformation(senderUserId, recipientUserId, information);
        return ResponseEntity.ok(sentInformation);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')") // Autorisation requise pour lire toutes les informations

    public ResponseEntity<List<Information>> getAllInformation() {
        List<Information> informationList = informationService.getAllInformation();
        return new ResponseEntity<>(informationList, HttpStatus.OK);
    }
}
