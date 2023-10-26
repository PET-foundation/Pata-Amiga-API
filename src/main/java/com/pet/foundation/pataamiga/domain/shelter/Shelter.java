package com.pet.foundation.pataamiga.domain.shelter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(length = 600, nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(length = 600)
    private String adoptionPolice;

    @Column(name = "profile_picture", length = 20000, columnDefinition = "BLOB")
    @Lob
    private String profilePicture;

    @Column(name = "banner", length = 20000, columnDefinition = "BLOB")
    @Lob
    private String banner;

    @Embedded
    private Contact contact = new Contact();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<User> owners;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
