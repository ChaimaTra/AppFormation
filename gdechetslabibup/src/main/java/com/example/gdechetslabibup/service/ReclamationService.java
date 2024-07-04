package com.example.gdechetslabibup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.gdechetslabibup.model.Reclamation;
import com.example.gdechetslabibup.repos.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamatiomRepository;
    

    public List<Reclamation> getAllReclamatonVisibleToUsers() {
        return reclamatiomRepository.findByVisibleToUsersTrue();
    }

    public Reclamation createReclamaton(Reclamation reclamation) {
       /* // Vérifier si l'utilisateur connecté est ADMIN
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            // Si l'utilisateur est ADMIN, rendre la réclamation visible aux utilisateurs
            reclamation.setVisibleToUsers(true);
        } else {
            // Si l'utilisateur n'est pas ADMIN, ne pas rendre la réclamation visible aux utilisateurs
            reclamation.setVisibleToUsers(false);
        } */ reclamation.setVisibleToUsers(true); 
        
        return reclamatiomRepository.save(reclamation);
    }

    public void deleteReclamaton(Long reclamationId) {
        reclamatiomRepository.deleteById(reclamationId);
    }
}
