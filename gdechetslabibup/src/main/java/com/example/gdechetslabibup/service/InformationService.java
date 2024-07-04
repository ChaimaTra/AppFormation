package com.example.gdechetslabibup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gdechetslabibup.model.Information;
import com.example.gdechetslabibup.model.Role;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.repos.InformationRepository;
import com.example.gdechetslabibup.repos.UserRepository;

@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Information sendInformation(Long senderUserId, Long recipientUserId, Information information) {
        User senderUser = userRepository.findById(senderUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User recipientUser = userRepository.findById(recipientUserId)
                .orElseThrow(() -> new RuntimeException("Recipient user not found"));

        // Vérifiez que le destinataire est un administrateur
        if (recipientUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Recipient must have ROLE_ADMIN");
        }

        information.setSender(senderUser);
        information.setRecipient(recipientUser);

        return informationRepository.save(information);
    }

    public List<Information> getAllInformation() {
        return informationRepository.findAll();
    }
}
