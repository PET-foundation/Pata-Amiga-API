package com.pet.foundation.pataamiga.swagger;

import lombok.Data;

@Data
public class SwaggerResponses {

    public static final String RESPONSE_201 = "201 Successful Registration";
    public static final String RESPONSE_200 = "200 Successful Login";

    public static final String RESPONSE_409 = "409 User Email Already Exists";
    public static final String RESPONSE_403 = "403 Forbidden";
    public static final String RESPONSE_404 = "404 Not Found";
    public static final String RESPONSE_400 = "400 Bad Request";


}
