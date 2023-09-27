package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;

import java.util.Date;

public class UserCreator {

    public static User returnValidUser() {
        return User.builder()
                .id(1L)
                .uuid("1")
                .name("Teste")
                .email("user@email.com")
                .profilePicture("profilePicture")
                .banner("banner")
                .contact(returnNewContatc())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

    }

    public static Contact returnNewContatc() {
        return Contact.builder()
                .phone("phone")
                .whatsapp("whatsapp")
                .instagram("instagram")
                .facebook("facebook")
                .build();
    }

    public static User returnToBeUpdated() {
        return User.builder()
                .name("Teste")
                .email("email@1.com")
                .profilePicture("profilePicture")
                .banner("banner")
                .contact(returnNewContatc())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

}
