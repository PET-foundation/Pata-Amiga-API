package com.pet.foundation.pataamiga.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contact {
    @Column
    private String phone;

    @Column
    private String whatsapp;

    @Column
    private String instagram;

    @Column
    private String facebook;
}
