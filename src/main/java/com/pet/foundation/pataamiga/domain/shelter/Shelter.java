package com.pet.foundation.pataamiga.domain.shelter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(length = 500)
    private String pixKey;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<User> owners;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    @JsonIgnore
    private List<Posts> posts;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.uuid = UUID.randomUUID();
        this.createdAt = new Date();
    }

}
