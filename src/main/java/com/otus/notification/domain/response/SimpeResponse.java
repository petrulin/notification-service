package com.otus.notification.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpeResponse {
    private String status;
    private String error;
}
