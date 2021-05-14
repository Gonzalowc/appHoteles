package edu.fpdual.hotelesapp.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
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


import lombok.Getter;
import lombok.Setter;

public class Sender {

	@Setter
	@Getter
	Properties mailProp = new Properties();

	@Setter
	@Getter
	Properties prop = new Properties();

	public Sender() {
		try {
			mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
			prop.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean send(String from, String to, String subject, String text, String content) throws FileNotFoundException, IOException {
		Session session = createSession();
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);

			BodyPart texto = new MimeBodyPart();
			texto.setText(text);

			File file = new File(content);
			
			InputStream fileData = getClass().getClassLoader().getResourceAsStream("mail.properties");
			
			try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
	            int read;
	            byte[] bytes = new byte[8192];
	            while ((read = fileData.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	        }
			
			BodyPart fichero = new MimeBodyPart();
			fichero.setDataHandler(new DataHandler(new FileDataSource(file)));
			fichero.setFileName(file.getName());

			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(texto);
			multiPart.addBodyPart(fichero);
			
			message.setContent(multiPart);
			
			System.out.println("ENVIANDO...");
			
			Transport.send(message);
			System.out.println("MENSAJE ENVIADO CON EXITO");
			
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}

	}


	private Session createSession() {
		Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(prop.getProperty(CredentialsConstants.USER),
						prop.getProperty(CredentialsConstants.PASSWD));
			}

		});


		session.setDebug(true);
		
		return session;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// new Sender().send("mi-email", "destinatario", "Asunto","Cuerpo","ruta-archivo");
		new Sender().send("hotelesapp@gmail.com", "alum.abonillag.2020@iesalixar.org", "Prueba", "Probando el envio de correos con Java", "C:\\Users\\angela.bonilla.gomez\\OneDrive - Accenture\\Desktop\\Angela\\Documentacion\\enviarCorreoJava.txt");

	}

}