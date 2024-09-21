package com.Store.Store.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

    private String message;
    private Object object;
}
