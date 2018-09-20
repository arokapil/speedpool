package com.redis.sample;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailJavaAPI {

       public static void sendEmail(String to) throws UnsupportedEncodingException {

        String from="sender@example.com";
        String host = "127.0.0.1"; // mail server host Properties properties = System.getProperties(); properties.setProperty("mail.smtp.host", host);

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "Dear Miner your rig is disconnected";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, "NoReply"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(to, "Mr. Miner"));
            msg.setSubject("Rig Disconnected");
            msg.setText(msgBody);
            Transport.send(msg);
            System.out.println("Email sent successfully...");

        } catch (AddressException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        } catch (MessagingException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
            
        }
    }
}