package com.example.gdechetslabibup.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long>{
    List<Reclamation> findByVisibleToUsersTrue();
    
}
