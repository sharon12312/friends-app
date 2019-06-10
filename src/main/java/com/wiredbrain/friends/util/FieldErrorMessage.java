package com.wiredbrain.friends.util;

import lombok.Data;

@Data
public class FieldErrorMessage {

    private String field;
    private String message;

    public FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
