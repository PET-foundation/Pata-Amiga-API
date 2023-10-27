package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterCreateDTO;
import com.pet.foundation.pataamiga.mapper.shelter.ShelterMapper;
import com.pet.foundation.pataamiga.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping
    public ResponseEntity<?> createShelter(@RequestBody ShelterCreateDTO shelterCreateDTO) {
        Shelter shelter = ShelterMapper.INSTANCE.toShelter(shelterCreateDTO);
        shelterService.createShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {
        return ResponseEntity.ok(this.shelterService.getAllShelters());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Shelter> getShelterByUUID(@PathVariable UUID uuid) {
        Shelter shelterFound = this.shelterService.getShelterByUUID(uuid).get();
        return ResponseEntity.ok(shelterFound);
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<List<Shelter>> getAllSheltersFromUser(@PathVariable String uuid) {
        return ResponseEntity.ok(this.shelterService.getAllSheltersFromUser(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateShelter(@PathVariable UUID uuid, @RequestBody ShelterCreateDTO shelterCreateDTO) {
        Shelter shelter = ShelterMapper.INSTANCE.toShelter(shelterCreateDTO);
        shelterService.updateShelter(uuid, shelter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteShelter(@PathVariable UUID uuid) {
        shelterService.deleteShelter(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
