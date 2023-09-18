package com.pet.foundation.pataamiga.exceptions.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDetails {
    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String developerMessage;
}
