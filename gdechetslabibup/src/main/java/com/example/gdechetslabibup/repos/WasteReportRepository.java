package com.example.gdechetslabibup.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gdechetslabibup.model.WasteReport;;

@Repository
public interface WasteReportRepository extends JpaRepository<WasteReport, Long> {
   
}
