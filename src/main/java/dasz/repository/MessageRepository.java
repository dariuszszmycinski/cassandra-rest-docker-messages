package dasz.repository;

import org.springframework.data.repository.CrudRepository;
import dasz.model.Message;

public interface MessageRepository extends CrudRepository<Message, String>, CustomizedSave<Message> {
}
