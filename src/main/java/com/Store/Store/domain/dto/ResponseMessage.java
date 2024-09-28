package com.Store.Store.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseMessage {

    private String message;
    private Object object;
}
