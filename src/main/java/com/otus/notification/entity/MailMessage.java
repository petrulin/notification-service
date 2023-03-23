package com.otus.notification.entity;

import com.otus.notification.domain.request.dto.CreateMailDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mail_message", schema = "notification_service", catalog = "postgres")
@NoArgsConstructor
public class MailMessage {

    private static final BigDecimal AMOUNT_DEFAULT_VALUE = BigDecimal.ZERO;
    private static final BigDecimal DISCOUNT_DEFAULT_VALUE = BigDecimal.ZERO;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "status", nullable = false)
    private String status;

    public MailMessage(CreateMailDTO mailDTO) {
        this.created = LocalDateTime.now();
        this.message = mailDTO.getMessage();
        this.email = mailDTO.getEmail();
        this.userName = mailDTO.getUsername();
        this.status = "DELIVERED";
    }

}
