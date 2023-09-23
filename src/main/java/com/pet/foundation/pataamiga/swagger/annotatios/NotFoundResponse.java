package com.pet.foundation.pataamiga.swagger.annotatios;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.pet.foundation.pataamiga.swagger.SwaggerResponses.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = CODE_400, description = RESPONSE_404, useReturnTypeSchema = true)
public @interface NotFoundResponse {
}
