package com.example.gdechetslabibup.dto;

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
public class RecyclingInfo {
    private String title;
    private String content;
    private String mediaUrl;

    public RecyclingInfo toEntity() {
        return RecyclingInfo.builder()
                .title(this.title)
                .content(this.content)
                .mediaUrl(this.mediaUrl)
                .build();
    }

    public static RecyclingInfo fromEntity(RecyclingInfo recyclingInfo) {
        return RecyclingInfo.builder()
                .title(recyclingInfo.getTitle())
                .content(recyclingInfo.getContent())
                .mediaUrl(recyclingInfo.getMediaUrl())
                .build();
    }
}
