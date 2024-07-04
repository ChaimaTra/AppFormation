package com.example.gdechetslabibup.repos;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gdechetslabibup.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
    Optional<User> findByQuartier(String quartier);

}
