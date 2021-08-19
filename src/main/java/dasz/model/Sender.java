package dasz.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public Sender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Message message){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(message.getEmail());
        msg.setSubject(message.getTitle());
        msg.setText(message.getContent());
        try{
            javaMailSender.send(msg);
        }catch (Exception e){
            System.err.println("Could not send email to "+message.getEmail());
        }

    }
}
