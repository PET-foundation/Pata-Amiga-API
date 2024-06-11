package com.pet.foundation.pataamiga.domain.email.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO {
    private String ownerRef;

    private String emailTo;

    private String emailFrom;

    private String subject;
    private String text;
    private MailType mailType;
}
