package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserUpdateDTO;

public class CreateUserDTOCreator {
    public static UserCreateDTO returnValidUserCreateDTO() {
        return new UserCreateDTO(
                UserCreator.returnValidUser().getName(),
                UserCreator.returnValidUser().getEmail(),
                UserCreator.returnValidUser().getPassword(),
                UserCreator.returnValidUser().getContact().getPhone(),
                UserCreator.returnValidUser().getContact().getWhatsapp(),
                UserCreator.returnValidUser().getContact().getInstagram(),
                UserCreator.returnValidUser().getContact().getFacebook(),
                UserCreator.returnValidUser().getProfilePicture(),
                UserCreator.returnValidUser().getBanner()
        );
    }

    public static UserUpdateDTO returnValidUserUpdateDTO() {
        return new UserUpdateDTO(
                UserCreator.returnToBeUpdated().getName(),
                UserCreator.returnToBeUpdated().getEmail(),
                UserCreator.returnToBeUpdated().getContact().getPhone(),
                UserCreator.returnToBeUpdated().getContact().getWhatsapp(),
                UserCreator.returnToBeUpdated().getContact().getInstagram(),
                UserCreator.returnToBeUpdated().getContact().getFacebook(),
                UserCreator.returnToBeUpdated().getProfilePicture(),
                UserCreator.returnToBeUpdated().getBanner()
        );
    }
}
