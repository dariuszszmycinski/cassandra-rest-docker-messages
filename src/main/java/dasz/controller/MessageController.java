package dasz.controller;

import dasz.model.Message;
import dasz.model.Sender;
import dasz.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private Sender sender;

    @PostMapping(value = "/send")
    @ResponseBody
    public List<Message> sendMessages(@RequestBody MagicalRequest magicalRequest) {
        return getMessages(magicalRequest.getMagic_number());
    }

    @Data
    private static class MagicalRequest {
        private Integer magic_number;
    }

    @PostMapping("/send/{magic_number}")
    public List<Message> sendMessagesWithUrlNumber(@PathVariable int magic_number) {
        return getMessages(magic_number);
    }

    private List<Message> getMessages(int magic_number) {
        Iterable<Message> messageList = messageRepository.findAll();
        List<Message> result = new ArrayList<>();
        for (Message m : messageList) {
            if (m.getMagic_number() == magic_number) {
                result.add(m);
                messageRepository.delete(m);
                sender.sendEmail(m);
            }
        }
        return result;
    }

    //add message to database
    @PostMapping(value = "/message")
    @ResponseBody
    public Message addMessage(@RequestBody Message newMessage) {
        messageRepository.save(newMessage, 300); //message will be deleted after 5 minutes in database
        return newMessage;
    }

    //list messages in database
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    //list messages to specific email from database
    @GetMapping("/messages/{emailValue}")
    public List<Message> getMessages(@PathVariable String emailValue) {
        Iterable<Message> result = messageRepository.findAll();
        List<Message> messageList = new ArrayList<>();
        for (Message m : result) {
            if (m.getEmail().equals(emailValue)) messageList.add(m);
        }
        return messageList;
    }

}
