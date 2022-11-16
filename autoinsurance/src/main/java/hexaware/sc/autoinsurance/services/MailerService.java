package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.domain.Claim;
import hexaware.sc.autoinsurance.domain.EmailSender;

public interface MailerService {
    
    void sendEmailClaim(EmailSender emailSender, Claim claim, String action);
}
