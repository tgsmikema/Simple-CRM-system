package application;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {


	public static void sendMail(String recepient,String theSubject,String theMessage) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// read email name and password from txt file.
		String myAccountEmail = FileReaderUtil.fileReader("emailDetails.txt").get(0);
		String password = FileReaderUtil.fileReader("emailDetails.txt").get(1);

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail,password);
			}
		});
		
		Message message = prepareMessage(session,myAccountEmail,recepient,theSubject,theMessage);

		Transport.send(message);
	}

	private static Message prepareMessage(Session session, String myAccountEmail,String recepient, String theSubject, String theMessage) {
		// TODO Auto-generated method stub
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(theSubject);
			message.setText(theMessage);
			return message;
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
		
	}

}
