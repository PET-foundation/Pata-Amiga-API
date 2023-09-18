package com.pet.foundation.pataamiga.domain.posts;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Info {
    @Column
    private String specie;

    @Column
    private String race;

    @Column
    private String sex;

    @Column
    private String age;

    @Column
    private String weight;

    @Column
    private boolean castrated;

    @Column
    private boolean vaccinated;

    @Column
    private boolean ungerminated;

    @Column
    private boolean pedigree;

    @Column
    private boolean specialNeeds;

    @Column
    private Port port;
}
