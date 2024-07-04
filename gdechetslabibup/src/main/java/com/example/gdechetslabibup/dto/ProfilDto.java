package com.example.gdechetslabibup.dto;

import com.example.gdechetslabibup.model.Profil;


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
public class ProfilDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public void updateEntity(Profil user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setAddress(this.address);
        user.setPhoneNumber(this.phoneNumber);
    }

    public Profil toEntity() {
        return Profil.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .build();
    }

    public static ProfilDto fromEntity(Profil user) {
        return ProfilDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
