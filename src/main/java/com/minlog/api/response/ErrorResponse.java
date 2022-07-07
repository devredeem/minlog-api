package com.minlog.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) // Json으로 넘길때 비어있는 값은 빼서 줄 수 있음
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation = new HashMap<>();
//    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
//        this.validation = validation;
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
