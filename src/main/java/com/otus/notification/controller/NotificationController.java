package com.otus.notification.controller;


import com.otus.notification.domain.request.dto.CreateMailDTO;
import com.otus.notification.domain.response.MailResponse;
import com.otus.notification.domain.response.dto.MailDTO;
import com.otus.notification.entity.MailMessage;
import com.otus.notification.error.ApplicationError;
import com.otus.notification.service.MailMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static java.util.stream.Collectors.toList;


@RequestMapping(value = "/notification-service/api/v1/internal/")
@RestController
public class NotificationController {

    private final ModelMapper modelMapper;

    private final MailMessageService mms;

    public NotificationController(MailMessageService mms) {
        this.mms = mms;
        this.modelMapper = new ModelMapper();
    }
    // TODO GET MAIL BY USERNAME
    @PostMapping(path = "/findEmail")
    public ResponseEntity<MailResponse> findEmail(@RequestBody CreateMailDTO mailDTO) {
        try {
            var mailsEntity = mms.findByUsername(mailDTO.getUsername());
            var mailsDTO = mailsEntity.stream()
                    .map(this::convertToMailDTO)
                    .collect(toList());
            return ResponseEntity.ok(new MailResponse(mailsDTO));
        } catch (Exception ex) {
            return new ResponseEntity<>(new MailResponse(ApplicationError.INTERNAL_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private MailDTO convertToMailDTO(MailMessage mailMessage) {
        return modelMapper.map(mailMessage, MailDTO.class);
    }

}
