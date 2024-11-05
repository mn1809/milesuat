package com.miles.Utilities;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendAttachmentInEmail 
{
   public void SendMail(String fileToPick , String date) throws IOException
   
   {   
      // Recipient's email ID needs to be mentioned.
      String to = "manojgowda839@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "manoj.hr@mileseducation.com";

      final String username = "manoj.hr@mileseducation.com";
      final String password = "junk";

      
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");
      //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() 
      {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
               return new PasswordAuthentication(username, password);
            }
       });

      try 
      {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from,"TestAutomation Reporter"));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("WebApp Automated Test Report - "+date);

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Hello All \n Please find the attached report \n Test cases executed on :"+date+"\n Rgds, \n Miles odoo QA Team");

         // Create a multipart message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
       //  String filename = fileToPick;
         DataSource source = new FileDataSource(fileToPick);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("AutomationTestReport_"+date);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("\n Mail Sent successfully....to :" +to);
  
      } catch (MessagingException e)
      {
         throw new RuntimeException(e);
      }
   }   
 

	}
   
   