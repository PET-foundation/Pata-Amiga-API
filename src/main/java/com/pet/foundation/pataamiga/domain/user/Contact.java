package com.pet.foundation.pataamiga.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

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
