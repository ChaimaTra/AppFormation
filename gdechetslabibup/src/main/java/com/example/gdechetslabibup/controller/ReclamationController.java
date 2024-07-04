package com.example.gdechetslabibup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.gdechetslabibup.model.Reclamation;
import com.example.gdechetslabibup.service.ReclamationService;
import static com.example.gdechetslabibup.racine.racine.root;
import java.util.List;

@RestController
@RequestMapping(value=root +"/reclamationAdmin")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")

public class ReclamationController {

    @Autowired
    private ReclamationService ReclamationService;

    @GetMapping("/visible-to-users")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public List<Reclamation> getAllClaimsVisibleToUsers() {
        return ReclamationService.getAllReclamatonVisibleToUsers();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = ReclamationService.createReclamaton(reclamation);
        return ResponseEntity.ok(createdReclamation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        ReclamationService.deleteReclamaton(id);
        return ResponseEntity.ok().build();
    }

    
}

