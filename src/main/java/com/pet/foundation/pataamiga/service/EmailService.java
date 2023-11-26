package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.email.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO);
}
