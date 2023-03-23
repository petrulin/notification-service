package com.otus.notification.domain.response;

import com.otus.notification.domain.response.dto.MailDTO;
import com.otus.notification.error.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MailResponse extends AResponse {
    private List<MailDTO> emails;

    public MailResponse(ApplicationError applicationError) {
        super(applicationError);
    }

}
