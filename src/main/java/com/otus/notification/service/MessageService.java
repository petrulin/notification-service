package com.otus.notification.service;

import com.otus.notification.entity.Message;


public interface MessageService {

    Message save(Message user);
    Message findById(String msgId);

}
