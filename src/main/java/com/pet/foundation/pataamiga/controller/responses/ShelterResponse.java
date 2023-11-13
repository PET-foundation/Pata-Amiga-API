package com.pet.foundation.pataamiga.controller.responses;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelterResponse {
    public Long id;
    public UUID uuid;
    public String name;
    public String description;
    public String location;
    public String adoptionPolice;
    public String profilePicture;
    public String banner;
    public Contact contact;
    public String pixKey;
    public List<String> owners;
    public Date createdAt;
    public Date updatedAt;

    public static ShelterResponse toResponse(Shelter shelter) {
        return new ShelterResponse(
                shelter.getId(),
                shelter.getUuid(),
                shelter.getName(),
                shelter.getDescription(),
                shelter.getLocation(),
                shelter.getAdoptionPolice(),
                shelter.getProfilePicture(),
                shelter.getBanner(),
                shelter.getContact(),
                shelter.getPixKey(),
                convertOwners(shelter.getOwners()),
                shelter.getCreatedAt(),
                shelter.getUpdatedAt()
        );
    }

    private static List<String> convertOwners(List<User> users) {
        List<String> owners = new ArrayList<>();

        for (User owner :
                users) {
            owners.add(owner.getUuid());
        }

        return owners;
    }
}
