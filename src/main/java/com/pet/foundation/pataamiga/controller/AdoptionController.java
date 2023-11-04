package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import com.pet.foundation.pataamiga.service.AdoptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adoptions")
@AllArgsConstructor
public class AdoptionController {
    private final AdoptionService adoptionService;

    @PostMapping("/adopt/{postId}/{userId}")
    public ResponseEntity<?> adopt(@PathVariable String postId, @PathVariable String userId) {
        adoptionService.adopt(postId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<List<Adoption>> getAllAdoptionsByUserUuid(@PathVariable String uuid) {
        List<Adoption> allByUserUuid = adoptionService.findAllByUserUuid(uuid);
        return ResponseEntity.ok(allByUserUuid);
    }
}
