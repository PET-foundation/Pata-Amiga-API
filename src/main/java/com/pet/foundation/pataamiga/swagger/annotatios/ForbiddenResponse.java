package com.pet.foundation.pataamiga.swagger.annotatios;

import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.pet.foundation.pataamiga.swagger.SwaggerResponses.*;
import static com.pet.foundation.pataamiga.swagger.SwaggerResponses.RESPONSE_403;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = CODE_403, description = RESPONSE_403,
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
})
public @interface ForbiddenResponse {
}
