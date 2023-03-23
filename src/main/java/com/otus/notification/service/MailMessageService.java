package com.otus.notification.service;

import com.otus.notification.domain.request.dto.CreateMailDTO;
import com.otus.notification.entity.MailMessage;

import java.util.List;


public interface MailMessageService {
    void createMail(CreateMailDTO mailDTO);
    List<MailMessage> findByUsername(String username);
}
