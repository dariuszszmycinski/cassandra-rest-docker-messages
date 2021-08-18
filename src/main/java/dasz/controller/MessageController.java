package dasz.controller;

import dasz.model.Message;
import dasz.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message newMessage) {
        String id = String.valueOf(new Random().nextInt());
        Message mess = new Message(id, newMessage.getEmail(), newMessage.getTitle(), newMessage.getContent(), newMessage.getMagic_number());
        messageRepository.save(mess);
        return mess;
    }

    @GetMapping("/messages")
    public List<Message> getMesseges() {
        Iterable<Message> result = messageRepository.findAll();
        List<Message> messageList = new ArrayList<>();
        result.forEach(messageList::add);
        return messageList;
    }


}
