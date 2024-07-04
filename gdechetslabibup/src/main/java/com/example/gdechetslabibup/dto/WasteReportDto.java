package com.example.gdechetslabibup.dto;

import com.example.gdechetslabibup.model.WasteReport;

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
public class WasteReportDto {
    private String description;
    private String photoUrl;

      public WasteReport toEntity() {
        return WasteReport.builder()
                .description(this.description)
                .photoUrl(this.photoUrl)
                .build();
    }

    public static WasteReportDto fromEntity(WasteReport wasteReport) {
        return WasteReportDto.builder()
                .description(wasteReport.getDescription())
                .photoUrl(wasteReport.getPhotoUrl())
                .build();
    }
}
