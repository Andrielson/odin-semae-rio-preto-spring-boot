package io.github.andrielson.spring.boot.odin.semaeriopreto.mailer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailerService {
    private final JavaMailSender mailSender;

    public void sendEmail(final EmailMessage message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom("andrielson@carroporassinatura.blog.br", "Diário Oficial - SeMAE Rio Preto");
            messageHelper.setTo(message.getTo());
            messageHelper.setSubject(message.getSubject());
            String t = message.getText();
            String h = message.getHtml();
            messageHelper.setText(t, h);
        };
        log.debug("##################################################################################################");
        System.out.println(message.getText());
        System.out.println(message.getHtml());
//        try {
//            mailSender.send(messagePreparator);
//            log.info("Activation email sent!");
//        } catch (MailException e) {
//            log.debug(e.getMessage());
//            log.error("Exception occurred when sending mail to " + message.getTo());
//        }
        //mailSender.send(messagePreparator);
    }
}
