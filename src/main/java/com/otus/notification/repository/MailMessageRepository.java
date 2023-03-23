package com.otus.notification.repository;

import com.otus.notification.entity.MailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {

    List<MailMessage> findAllByUserName(String userName);
}
