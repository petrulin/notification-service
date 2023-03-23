package com.otus.notification.service.impl;


import com.otus.notification.domain.request.dto.CreateMailDTO;
import com.otus.notification.entity.MailMessage;
import com.otus.notification.repository.MailMessageRepository;
import com.otus.notification.service.MailMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("mailMessageService")
@RequiredArgsConstructor
public class MailMessageServiceImpl implements MailMessageService {
    private final MailMessageRepository mailMessageRepository;

    @Override
    public void createMail(CreateMailDTO mailDTO) {
        mailMessageRepository.save(new MailMessage(mailDTO));
    }

    @Override
    public List<MailMessage> findByUsername(String username) {
        return mailMessageRepository.findAllByUserName(username);
    }


}
