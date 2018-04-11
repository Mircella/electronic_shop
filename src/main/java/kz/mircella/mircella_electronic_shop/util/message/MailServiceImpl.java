package kz.mircella.mircella_electronic_shop.util.message;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class MailServiceImpl {
    private final JavaMailSenderImpl javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) {
        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("mail", context);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            mimeMessageHelper.setTo(mail.getTo());
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(mail.getFrom());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
