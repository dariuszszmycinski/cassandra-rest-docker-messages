package dasz.controller;

import dasz.model.Message;
import dasz.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @PostMapping("/send")
    public List<Message> sendMessages(){
        List<Message> messageList = (List<Message>) messageRepository.findAll();
        messageRepository.deleteAll();
        return messageList;
    }

    @PostMapping("/send/{magic_number}")
    public List<Message> sendMessages(@PathVariable int magic_number){
        Iterable<Message> messageList = messageRepository.findAll();
        List<Message> result = new ArrayList<>();
        for (Message m:messageList) {
            if (m.getMagic_number()==magic_number){
                result.add(m);
                messageRepository.delete(m);
            }
        }
        return result;
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message newMessage) {
        messageRepository.save(newMessage,300); //message will be deleted after 5 minutes in database
        return newMessage;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    @GetMapping("/messages/{emailValue}")
    public List<Message> getMessages(@PathVariable String emailValue) {
        Iterable<Message> result = messageRepository.findAll();
        List<Message> messageList = new ArrayList<>();
        for (Message m : result) {
            if (m.getEmail().equals(emailValue)) {
                messageList.add(m);
            }
        }
        return messageList;
    }


}
