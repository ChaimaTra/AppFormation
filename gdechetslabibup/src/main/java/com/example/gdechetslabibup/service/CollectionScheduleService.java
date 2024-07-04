
package com.example.gdechetslabibup.service;

import com.example.gdechetslabibup.dto.CollectionScheduleDto;
import com.example.gdechetslabibup.model.CollectionSchedule;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.repos.CollectionScheduleRepository;
import com.example.gdechetslabibup.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionScheduleService {

    private final CollectionScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CollectionScheduleService(CollectionScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public CollectionSchedule createCollectionSchedule(CollectionScheduleDto scheduleDto) {
        // Trouver l'utilisateur par quartier
        Optional<User> userOptional = userRepository.findByQuartier(scheduleDto.getQuartier());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Quartier not found in users");
        }

        // Créer un nouvel objet CollectionSchedule à partir du DTO
        CollectionSchedule newSchedule = CollectionSchedule.builder()
                .quartier(scheduleDto.getQuartier())
                .dateDeCollect(scheduleDto.getDateDeCollect())
                .user(userOptional.get())
                .build();

        // Enregistrer le nouvel objet dans la base de données
        return scheduleRepository.save(newSchedule);
    }

    public List<CollectionSchedule> findByQuartier(String quartier) {
        return scheduleRepository.findByQuartier(quartier);
    }

    public List<CollectionSchedule> findByDateDeCollect(LocalDate dateDeCollect) {
        return scheduleRepository.findByDateDeCollect(dateDeCollect);
    }

    public Optional<CollectionSchedule> findByQuartierAndDateDeCollect(String quartier, LocalDate dateDeCollect) {
        return scheduleRepository.findByQuartierAndDateDeCollect(quartier,dateDeCollect);
    }

    public List<CollectionSchedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Optional<CollectionSchedule> getById(Long id) {
        return scheduleRepository.findById(id);
    }

    public CollectionSchedule updateCollectionSchedule(Long id, CollectionScheduleDto scheduleDto) {
        Optional<CollectionSchedule> optionalSchedule = scheduleRepository.findById(id);
        if (!optionalSchedule.isPresent()) {
            throw new RuntimeException("CollectionSchedule not found");
        }

        CollectionSchedule existingSchedule = optionalSchedule.get();
        existingSchedule.setQuartier(scheduleDto.getQuartier());
        existingSchedule.setDateDeCollect(scheduleDto.getDateDeCollect());

        Optional<User> userOptional = userRepository.findByQuartier(scheduleDto.getQuartier());
        if (userOptional.isPresent()) {
            existingSchedule.setUser(userOptional.get());
        }

        return scheduleRepository.save(existingSchedule);
    }

    public void deleteCollectionSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
