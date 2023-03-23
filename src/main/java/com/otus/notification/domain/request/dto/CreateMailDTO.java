package com.otus.notification.domain.request.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMailDTO {

    private String username;
    private String message;
    private String email;
}
