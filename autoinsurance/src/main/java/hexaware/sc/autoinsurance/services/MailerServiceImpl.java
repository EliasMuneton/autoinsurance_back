package hexaware.sc.autoinsurance.services;

import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.domain.EmailSender;
import hexaware.sc.autoinsurance.helper.MailerHelper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MailerServiceImpl  implements MailerService{

  @Autowired
  private MailerHelper mailerHelper;

  @Async
  @Override
  public void sendEmail(EmailSender emailSender) {
    mailerHelper.sendMail(emailSender);
  }
}