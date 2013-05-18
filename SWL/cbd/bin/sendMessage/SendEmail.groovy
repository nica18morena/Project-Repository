/**
*Resource: https://javamail.java.net/nonav/docs/api/
*/
package sendMessage

import javax.mail.*
import javax.mail.internet.*
//import javax.mail.internet.InternetAddress
//import javax.mail.internet.MimeMessage
 
class SendEmail {
 
	private String username, password, host = "smtp.gmail.com"
 
	//Constructor
	SendEmail() {
		username = "sma4plu@gmail.com"
		password = "PacificLutheranUniversity"
	}

/**
*sends the message
*to: the recipients address
*sub: subject of the message
*crs: course in refrence
*/
	void sendMail(String to, String sub, String crs){
		String subject = "Class Availability"
		String message = "An opening for "+ sub +" "+ crs +" has been detected " +
				"to sign up for this course, log into Banner or go to the Registration office."
		def props = new Properties();
		props.put("mail.smtps.auth", "true");
 
		def session = Session.getDefaultInstance( props, null);
 
		def msg = new MimeMessage(session);
 
		msg.setSubject(subject);
		msg.setText(message);
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, to));
 
		def transport = session.getTransport("smtps");
 
		try {
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
	   }
		catch (Exception e) {
			println "Error"
	   }
	}
}

