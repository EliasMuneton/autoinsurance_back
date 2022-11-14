package hexaware.sc.autoinsurance.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import hexaware.sc.autoinsurance.domain.EmailSender;

@Component
public class MailerHelper {
  private static final Logger logger = LoggerFactory.getLogger(MailerHelper.class);

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  public void sendMail(EmailSender emailSender) {

    try {

      SimpleMailMessage mailMessage = new SimpleMailMessage();

      mailMessage.setFrom(sender);
      mailMessage.setTo(emailSender.getRecipient());
      mailMessage.setText(emailSender.getMsgBody());
      mailMessage.setSubject(emailSender.getSubject());

      javaMailSender.send(mailMessage);
      logger.info("Message Seded");
    }

    catch (Exception e) {
      logger.info("Error while Sending Mail ---  " + e.getMessage());
    }
  }
}