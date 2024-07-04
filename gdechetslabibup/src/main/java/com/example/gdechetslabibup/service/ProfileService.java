package com.example.gdechetslabibup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gdechetslabibup.model.Profil;
import com.example.gdechetslabibup.repos.ProfileRepository;
import com.example.gdechetslabibup.exception.EntityNotFoundException;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profil createProfile(Profil profile) {
        return profileRepository.save(profile);
    }

    public Profil updateProfile(Long profileId, Profil profileDetails) {
        Profil profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + profileId));

        profile.setFirstName(profileDetails.getFirstName());
        profile.setLastName(profileDetails.getLastName());
        profile.setAddress(profileDetails.getAddress());
        profile.setPhoneNumber(profileDetails.getPhoneNumber());
        

        return profileRepository.save(profile);
    }

    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }

    public Profil getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + profileId));
    }

    public List<Profil> getAllProfiles() {
        return profileRepository.findAll();
    }
}
