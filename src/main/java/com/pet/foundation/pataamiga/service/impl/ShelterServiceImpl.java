package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.exceptions.ShelterNotFoundException;
import com.pet.foundation.pataamiga.repositories.ShelterRepository;
import com.pet.foundation.pataamiga.service.ShelterService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private  UserService userService;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public List<Shelter> getAllSheltersFromUser(String uuid) {
        return shelterRepository.findSheltersByOwnersUuid(uuid);
    }

    @Override
    public Optional<Shelter> getShelterByUUID(UUID uuid) {
        return shelterRepository.findShelterByUuid(uuid)
                .map(Optional::of)
                .orElseThrow(() -> new ShelterNotFoundException("Shelter not found"));
    }

    @Override
    public void createShelter(Shelter shelter) {
        List<User> owners = new ArrayList<>();
        shelter.getOwners()
                .stream()
                .map(user -> userService.getUserByUuid(user.getUuid()))
                .forEach(owners::add);
        shelter.setOwners(owners);
        shelterRepository.save(shelter);
    }

    @Override
    public void updateShelter(UUID uuid, Shelter shelter) {
        Shelter shelterToUpdate = this.getShelterByUUID(uuid).get();
        BeanUtils.copyProperties(shelter, shelterToUpdate, "uuid", "id", "owners");
        shelterRepository.save(shelterToUpdate);
    }

    @Override
    public void deleteShelter(UUID uuid) {
        Shelter shelterToDelete = this.getShelterByUUID(uuid).get();
        shelterRepository.delete(shelterToDelete);
    }
}
