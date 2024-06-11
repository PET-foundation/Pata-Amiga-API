package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.email.Email;
import com.pet.foundation.pataamiga.domain.email.StatusEmail;
import com.pet.foundation.pataamiga.domain.email.dto.EmailDTO;
import com.pet.foundation.pataamiga.repositories.EmailRepository;
import com.pet.foundation.pataamiga.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        email.setSendDataEmail(LocalDateTime.now());

        try {
            String html = chooseTemplate(emailDTO);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(email.getEmailFrom());
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(html, true);

            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MessagingException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }

    private String chooseTemplate(EmailDTO emailDTO) {
        String html = "";
        switch (emailDTO.getMailType()) {
            case CREATE_USER:
                html = getHtmlFromTemplate(emailDTO, "email");
                break;
            case CHANGE_PASSWORD:
                html = getHtmlFromTemplate(emailDTO, "");
                break;
            default:
                break;
        }
        return html;
    }

    private String getHtmlFromTemplate(EmailDTO emailDTO, String templateName) {
        Context context = new Context();
        context.setVariable("title", emailDTO.getSubject());
        context.setVariable("message", emailDTO.getText());
        return templateEngine.process(templateName, context);
    }
}
