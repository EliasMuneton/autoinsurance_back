package hexaware.sc.autoinsurance.helper;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import hexaware.sc.autoinsurance.domain.Claim;
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
      MimeMessage mail = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setFrom(sender);
      
      helper.setTo(emailSender.getRecipient());
      helper.setSubject(emailSender.getSubject());
      helper.setText(emailSender.getMsgBody(), true);
      javaMailSender.send(mail);
      logger.info("Message Seded");
    }

    catch (Exception e) {
      logger.info(e.getMessage());
    }
  }

  public String htmlClaim (Claim claim, String action) {
     return " <!DOCTYPE html> "
     + " <html> "
     + " <title>Email</title> "
     + " <head> "
     + " </head> "
     + " <body> "
     + "   <div style='"
     + "       font-family: Arial, Helvetica, sans-serif; "
     + "       box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); "
     + "       transition: 0.3s; "
     + "       max-width: 400px; "
     + "       display: block; "
     + "       margin-left: auto; "
     + "       margin-right: auto "
     + "'> "
     + "     <img  src='https://insurance-b2c-assets.s3.ap-south-1.amazonaws.com/uploads/news/image/mceu_90504177311642250059136_1642250060.jpg'"
     + "       alt='Image' style='width:100%'> "
     + "     <div style=' padding: 2px 16px;'> "
     + "       <h4><b>Hi There!</b></h4> "
     + "       <center> "
     + "         <p style='font-size:20px'>Auto Insurance Claim have been <b>" + action.toUpperCase() + "</b> </p> "
     + "       </center> "
     + "       <p style='font-size:17px'><b>Topic:</b> " + claim.getClaimSubject().getDescription() + "</p> "
     + "       <p style='font-size:17px'><b>User:</b> " + claim.getUser().getFirstName() + " " + claim.getUser().getLastName() + "</p> "
     + "       <p style='font-size:17px'><b>Status:</b> " + claim.getClaimStatus().getDescription() + " </p> "
     + "     </div> "
     + "   </div> "
     + " </body> "
     + " </html> ";
  }
}