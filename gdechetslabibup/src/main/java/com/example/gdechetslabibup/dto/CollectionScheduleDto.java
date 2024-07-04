package com.example.gdechetslabibup.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.gdechetslabibup.model.CollectionSchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionScheduleDto {
        private String quartier;
    private LocalDate dateDeCollect;
    
    public CollectionSchedule toEntity() {
        return CollectionSchedule.builder()
                .quartier(this.quartier)
                .dateDeCollect(this.dateDeCollect)
                .build();
    }

    public static CollectionScheduleDto fromEntity(CollectionSchedule collectionSchedule) {
        return CollectionScheduleDto.builder()
                .quartier(collectionSchedule.getQuartier())
                .dateDeCollect(collectionSchedule.getDateDeCollect())
                .build();
    }
}
