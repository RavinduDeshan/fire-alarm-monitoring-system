// package net.codeJava.demofire;

// import javax.mail.*;
// import javax.mail.internet.*;
// import java.util.*;


// //This class Hold functions and propertis of the Email Generator
// public class EmailGenerator {

// 	//senders email
// 	final String senderEmail = "grpsender@gmail.com";
// 	//senders password
// 	final String senderPassword = "grpGRP123";
// 	//mail server
// 	final String Server = "smtp.gmail.com";
// 	//port number
// 	final String Port = "465";
// 	//Receiver Email
// 	String receiverEmail = null;
// 	//email subject
// 	static String Subject ;
// 	//email body
// 	static String Body;

// 	public EmailGenerator(String receiverEmail, String Subject, String Body) {


// 		this.receiverEmail = receiverEmail;
// 		this.Subject = Subject;
// 		this.Body = Body;

// 		try {

// 			//set properties
			
// 			Properties properties = new Properties();
// 			properties.put("mail.smtp.user", senderEmail);
// 			properties.put("mail.smtp.host", Server);
// 			properties.put("mail.smtp.port", Port);
// 			properties.put("mail.smtp.starttls.enable", "true");
// 			properties.put("mail.smtp.auth", "true");
// 			properties.put("mail.smtp.socketFactory.port", Port);
// 			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
// 			properties.put("mail.smtp.socketFactory.fallback", "false");
// 			SecurityManager security = System.getSecurityManager();

// 			//generate a authenticator
			
// 			Authenticator authentiator = new Authenticator();
			
// 			//get a session
			
// 			Session session = Session.getInstance(properties, authentiator);
			
// 			//generate message instance from session
			
// 			MimeMessage msg = new MimeMessage(session);
			
// 			//set message properties
			
// 			msg.setContent(this.Body, "text/html");
// 			msg.setSubject(this.Subject);
// 			msg.setFrom(new InternetAddress(senderEmail));
// 			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.receiverEmail));
			
// 			//send Email
// 			Transport.send(msg);
			
// 			System.out.println("Email Sent");
// 		}

// 		catch (Exception e) {
// 			System.out.println("Error");
// 		}

// 	}
	
	
	
// //authenticator Class for sender email authentication
// 	public class Authenticator extends javax.mail.Authenticator {
// 		public PasswordAuthentication getPasswordAuthentication() {
			
// 			//return authentication result status
// 			return new PasswordAuthentication(senderEmail, senderPassword);
// 		}
// 	}
// }