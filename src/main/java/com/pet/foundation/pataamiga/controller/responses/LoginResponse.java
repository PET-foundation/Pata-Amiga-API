package com.pet.foundation.pataamiga.controller.responses;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
