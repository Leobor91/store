package com.Store.Store.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

    private String message;
    private Object object;
}
