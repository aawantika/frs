package edu.gatech.financialapplication;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.Log;

/**
 * This class creates an e-mail and send it out to the user.
 * 
 * @author Team 15
 */

public class GMail {

    /**
     * E-mail Port constant instance variable.
     */
    private final String emailPort = "587"; // gmail's smtp port
   
    /**
     * smtpAuth constant instance variable.
     */
    //CHECKSTYLE:OFF
    private final String smtpAuth = "true"; //string necessary
    //CHECKSTYLE:ON
    
    /**
     * starttls instance variable.
     */
    private final String starttls = "true";
    
    /**
     * emailHost constant instance variable.
     */
    private final String emailHost = "smtp.gmail.com";
   
    /**
     * fromUser constant instance variable.
     */
    private final String fromUser = "cs2340frs@gmail.com";
   
    /**
     * fromPassword constant instance variable.
     */
    private final String fromPassword = "foobarsribshack";
    
    /**
     * emailSubject constant instance variable.
     */
    private final String emailSubject = "CS 2340 Forgotten Password";

    /**
     * toEmailList instance variable.
     */
    private List<String> toEmailList;
    
    /**
     * emailBody instance variable.
     */
    private String emailBody;

    /**
     * emailProperties instance variable.
     */
    private Properties emailProperties;
   
    /**
     * mailSession instance variable.
     */
    private Session mailSession;
    
    /**
     * emailMessage instance variable.
     */
    private MimeMessage emailMessage;
    
    /**
     * gmail instance variable.
     */
    private String gmail;

    /**
     * Default Constructor.
     */
    public GMail() {
    }

    /**
     * Creates the email to send out to all the users.
     * 
     * @param aToEmailList is the list of people the email would be sent out to.
     * @param aEmailBody is what the email says.
     */
    public GMail(List<String> aToEmailList, String aEmailBody) {
        this.toEmailList = aToEmailList;
        this.emailBody = aEmailBody;
        this.gmail = "Gmail";

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        Log.i(gmail, "Mail server properties set.");
    }

    /**
     * Creates the email message and sends it to the users.
     * 
     * @return MimeMessage the message to send to all the users.
     * @throws AddressException - in case the address is not correct
     * @throws MessagingException - in case the message is not correct
     * @throws UnsupportedEncodingException - in case the the encoding is
     * unsupported
     */
    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        emailMessage.setFrom(new InternetAddress(fromUser, fromUser));

        for (String toEmail : toEmailList) {
            Log.i(gmail, "toEmail: " + toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html"); // for a html email
        // emailMessage.setText(emailBody); // for a text email
        Log.i(gmail, "Email Message created.");
        return emailMessage;
    }

    /**
     * Sends an e-mail out to the user.
     *
     * @throws AddressException - in case the address is not correct
     * @throws MessagingException - in case the message is not correct
     */
    public void sendEmail() throws AddressException, MessagingException {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromPassword);
        Log.i(gmail, "allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        Log.i("GMail", "Email sent successfully.");
    }
}
