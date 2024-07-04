package com.example.gdechetslabibup.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
}

