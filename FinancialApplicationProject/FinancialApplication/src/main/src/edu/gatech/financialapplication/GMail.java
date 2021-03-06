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

	final String emailPort = "25";// gmail's smtp port
	final String smtpAuth = "true";
	final String starttls = "true";
	final String emailHost = "smtp.gmail.com";

	private final String FROM_USER = "cs2340frs@gmail.com";
	private final String FROM_PASSWORD = "foobarsribshack";
	private final String EMAIL_SUBJECT = "CS 2340 Forgotten Password";

	private List<String> toEmailList;
	private String emailBody;
	private Properties emailProperties;
	private Session mailSession;
	private MimeMessage emailMessage;

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
	public GMail(List<String> toEmailList, String emailBody) {
		this.toEmailList = toEmailList;
		this.emailBody = emailBody;

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", smtpAuth);
		emailProperties.put("mail.smtp.starttls.enable", starttls);
		Log.i("GMail", "Mail server properties set.");
	}

	/**
	 * Creates the email message and sends it to the users.
	 * 
	 * @return MimeMessage the message to send to all the users.
	 * @throws AddressException - in case the address is not correct
	 * @throws MessagingException - in case the message is not correct
	 * @throws UnsupportedEncodingException - in case the the encoding is unsupported
	 */
	public MimeMessage createEmailMessage() throws AddressException,
			MessagingException, UnsupportedEncodingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		mailSession.setDebug(true);
		emailMessage = new MimeMessage(mailSession);
		emailMessage.setFrom(new InternetAddress(FROM_USER, FROM_USER));

		for (final String toEmail : toEmailList) {
			Log.i("email", "toEmail: " + toEmail);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		}

		emailMessage.setSubject(EMAIL_SUBJECT);
		emailMessage.setContent(emailBody, "text/html"); // for a html email
		// emailMessage.setText(emailBody); // for a text email
		Log.i("email", "Email Message created.");
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
		transport.connect(emailHost, FROM_USER, FROM_PASSWORD);
		Log.i("gmail", "allrecipients: " + emailMessage.getAllRecipients());
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		Log.i("GMail", "Email sent successfully.");
	}
}
