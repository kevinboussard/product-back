package com.alten.api.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class AltenErrorMessage {
    private Date timestamp;
    private String message;
    private String description;
}
