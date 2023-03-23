package com.otus.notification.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.notification.domain.request.dto.CreateMailDTO;
import com.otus.notification.entity.Message;
import com.otus.notification.rabbitmq.domain.RMessage;
import com.otus.notification.service.MailMessageService;
import com.otus.notification.service.MessageService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueListener {

    private final MessageService messageService;
    private final MailMessageService notificationService;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.queues.service-queue}", ackMode = "MANUAL")
    public void orderQueueListener(RMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            var om = new ObjectMapper();
            var msg = messageService.findById(message.getMsgId());
            if (msg == null) {
                messageService.save(new Message(message.getMsgId()));
                switch (message.getCmd()) {
                    case "email" ->  {
                        om.getTypeFactory().constructCollectionType(ArrayList.class, CreateMailDTO.class);
                        var mailDTO = om.convertValue(message.getMessage(), CreateMailDTO.class);
                        notificationService.createMail(mailDTO);
                    }
                    default -> log.warn("::NotificationService:: rabbitmq listener method. Unknown message type");
                }
            }
            else {
                log.warn("::NotificationService:: rabbitmq listener method orderQueueListener duplicate message!");
            }
        } catch (Exception ex) {
            log.error("::NotificationService:: rabbitmq listener method orderQueueListener with error message {}", ex.getLocalizedMessage());
            log.error("::NotificationService:: rabbitmq listener method orderQueueListener with stackTrace {}", ExceptionUtils.getStackTrace(ex));
        } finally {
            basicAck(channel, tag, true);
        }
    }

    private void basicAck(Channel channel, Long tag, boolean b) {
        try {
            channel.basicAck(tag, b);
        } catch (IOException ex) {
            log.error("::NotificationService:: rabbitmq listener method basicAck with stackTrace {}", ExceptionUtils.getStackTrace(ex));
        }
    }
}
