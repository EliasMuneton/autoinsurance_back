package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.domain.EmailSender;

public interface MailerService {
    
    void sendEmail(EmailSender emailSender);
}
