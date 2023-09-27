package com.pet.foundation.pataamiga.exceptions.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionDetails {
    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String developerMessage;
    private String fields;
    private String fieldsMessage;
}
