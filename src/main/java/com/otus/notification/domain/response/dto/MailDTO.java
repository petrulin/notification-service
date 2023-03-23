package com.otus.notification.domain.response.dto;


import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    private String username;
    private String message;
    private String email;
    private String status;
}
