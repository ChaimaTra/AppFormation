package com.example.gdechetslabibup.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="WasteReport")
public class WasteReport {
   
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String description;
 private String photoUrl;
 private String category;
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
 
   @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private WasteRapportStatus status;


 @ManyToOne
 @JoinColumn(name = "userWR_id")
 private User userWR;
}
