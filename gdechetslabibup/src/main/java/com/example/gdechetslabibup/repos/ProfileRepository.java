package com.example.gdechetslabibup.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.Profil;

@Repository
public interface ProfileRepository extends JpaRepository<Profil, Long> {
    
}