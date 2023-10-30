package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterCreateDTO;
import com.pet.foundation.pataamiga.mapper.shelter.ShelterMapper;
import com.pet.foundation.pataamiga.service.ShelterService;
import com.pet.foundation.pataamiga.swagger.annotatios.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping
    @Operation(summary = "Create a new shelter")
    @Tag(name = "Shelter")
    @CreatedResponse
    @ForbiddenResponse
    public ResponseEntity<?> createShelter(@RequestBody ShelterCreateDTO shelterCreateDTO) {
        Shelter shelter = ShelterMapper.INSTANCE.toShelter(shelterCreateDTO);
        shelterService.createShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all shelters")
    @Tag(name = "Shelter")
    @OkResponse
    @ForbiddenResponse
    public ResponseEntity<List<Shelter>> getAllShelters() {
        return ResponseEntity.ok(this.shelterService.getAllShelters());
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get shelter by UUID")
    @Tag(name = "Shelter")
    @OkResponse
    @NotFoundResponse
    @ForbiddenResponse
    public ResponseEntity<Shelter> getShelterByUUID(@PathVariable UUID uuid) {
        Shelter shelterFound = this.shelterService.getShelterByUUID(uuid).get();
        return ResponseEntity.ok(shelterFound);
    }

    @GetMapping("/user/{uuid}")
    @Operation(summary = "Get all shelters from user")
    @Tag(name = "Shelter")
    @OkResponse
    @NotFoundResponse
    @ForbiddenResponse
    public ResponseEntity<List<Shelter>> getAllSheltersFromUser(@PathVariable String uuid) {
        return ResponseEntity.ok(this.shelterService.getAllSheltersFromUser(uuid));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update shelter by UUID")
    @Tag(name = "Shelter")
    @NoContentResponse
    @NotFoundResponse
    @ForbiddenResponse
    public ResponseEntity<?> updateShelter(@PathVariable UUID uuid, @RequestBody ShelterCreateDTO shelterCreateDTO) {
        Shelter shelter = ShelterMapper.INSTANCE.toShelter(shelterCreateDTO);
        shelterService.updateShelter(uuid, shelter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete shelter by UUID")
    @Tag(name = "Shelter")
    @NoContentResponse
    @NotFoundResponse
    @ForbiddenResponse
    public ResponseEntity<?> deleteShelter(@PathVariable UUID uuid) {
        shelterService.deleteShelter(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
