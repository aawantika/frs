package edu.gatech.financialapplication;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.AndroidRuntimeException;

/**
 * A class that sets all the necessary forwarding
 * and message creation for emailing lost passwords.
 * @author Team 15
 */
public class ForgotPassword {

	private static final String EMAIL_USERNAME = "cs2340frs@gmail.com";
    private static final String EMAIL_PASSWORD = "foobarsribshack";

    /**
     * Default constructor for ForgotPassword.
     */
    public ForgotPassword() {
    }

    /**
     * Sends a default message from the admin email account.
     * @param email The email to send to
     * @param password The password to be included.
     */
    public void sendMessage(final String email, final String password) {
        final Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        //CHECKSTYLE:OFF
        props.put("mail.smtp.socketFactory.port", "465"); //String literal necessary
        //CHECKSTYLE:ON
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        final Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_USERNAME,
                                EMAIL_PASSWORD);
                    }
                });

        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new AndroidRuntimeException(e);
        }
    }
}
