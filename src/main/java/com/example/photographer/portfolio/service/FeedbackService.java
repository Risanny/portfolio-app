package com.example.photographer.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.photographer.portfolio.dto.FeedbackDto;

@Service
public class FeedbackService {

    private final JavaMailSender mailSender;

    // Получаем из application.properties
    @Value("${spring.mail.username}")
    private String mailSenderUsername;

    // Куда отправлять письмо
    @Value("${app.photographer.email}")
    private String photographerEmail;

    public FeedbackService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendFeedback(FeedbackDto dto) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailSenderUsername);
        msg.setTo(photographerEmail);
        msg.setSubject("Новая заявка: " + dto.getSessionType());
        msg.setText(buildBody(dto));
        mailSender.send(msg);
    }

    private String buildBody(FeedbackDto dto) {
        return String.format(
                "Имя: %s%nТелефон: %s%nEmail: %s%nТип сессии: %s%nКомментарий: %s",
                dto.getName(), dto.getPhone(), dto.getEmail(),
                dto.getSessionType(), dto.getComment()
        );
    }
}
