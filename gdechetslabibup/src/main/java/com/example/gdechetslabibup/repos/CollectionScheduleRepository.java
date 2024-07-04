package com.example.gdechetslabibup.repos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.CollectionSchedule;

@Repository
public interface CollectionScheduleRepository extends JpaRepository<CollectionSchedule, Long> {
    List<CollectionSchedule> findByQuartier(String quartier);

    // Méthode pour trouver les jours de collecte par date
    List<CollectionSchedule> findByDateDeCollect(LocalDate dateDeCollect);

    // Méthode pour trouver les jours de collecte par quartier et date
    Optional<CollectionSchedule> findByQuartierAndDateDeCollect(String quartier, LocalDate dateDeCollect);

}