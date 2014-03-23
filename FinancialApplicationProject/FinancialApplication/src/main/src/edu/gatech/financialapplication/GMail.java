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

public class GMail {

	final private String emailPort = "587";// gmail's smtp port
	final private String smtpAuth = "true";
	final private String starttls = "true";
	final private String emailHost = "smtp.gmail.com";
	final private String fromUser = "cs2340frs@gmail.com";
	final private String fromPassword = "foobarsribshack";
	final private String emailSubject = "CS 2340 Forgotten Password";

	private List<String> toEmailList;	
	private String emailBody;

	private Properties emailProperties;
	private Session mailSession;
	private MimeMessage emailMessage;

	public GMail() {
	}

	public GMail(List<String> toEmailList, String emailBody) {
		this.toEmailList = toEmailList;
		this.emailBody = emailBody;

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", smtpAuth);
		emailProperties.put("mail.smtp.starttls.enable", starttls);
		Log.i("GMail", "Mail server properties set.");
	}

	public MimeMessage createEmailMessage() throws AddressException,
			MessagingException, UnsupportedEncodingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);
		emailMessage.setFrom(new InternetAddress(fromUser, fromUser));
		
		for (String toEmail : toEmailList) {
			Log.i("GMail", "toEmail: " + toEmail);
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html"); // for a html email
		// emailMessage.setText(emailBody); // for a text email
		Log.i("GMail", "Email Message created.");
		return emailMessage;
	}

	public void sendEmail() throws AddressException, MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromPassword);
		Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		Log.i("GMail", "Email sent successfully.");
	}
}
