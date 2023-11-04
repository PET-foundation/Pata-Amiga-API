package com.pet.foundation.pataamiga.mapper.shelter;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterCreateDTO;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterUpdateDTO;
import com.pet.foundation.pataamiga.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterMapper {

    ShelterMapper INSTANCE = Mappers.getMapper(ShelterMapper.class);

    Shelter toShelter(ShelterCreateDTO shelter);

    Shelter toShelter(ShelterUpdateDTO shelter);

    default List<User> toUserList(List<String> ownerIds) {
        if (ownerIds == null) {
            return null;
        }

        return ownerIds.stream()
                .map(ownerId -> {
                    User user = new User();
                    user.setUuid(ownerId);
                    return user;
                }).toList();
    }
}
