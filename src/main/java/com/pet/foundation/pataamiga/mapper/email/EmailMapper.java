package com.pet.foundation.pataamiga.mapper.email;

import com.pet.foundation.pataamiga.domain.email.Email;
import com.pet.foundation.pataamiga.domain.email.dto.EmailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    Email toEntity(EmailDTO emailDTO);
}
