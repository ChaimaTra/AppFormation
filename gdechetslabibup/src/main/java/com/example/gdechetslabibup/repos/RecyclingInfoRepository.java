package com.example.gdechetslabibup.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.RecyclingInfo;;

@Repository
public interface RecyclingInfoRepository extends JpaRepository<RecyclingInfo, Long> {
    
}

