package dasz.repository;

import org.springframework.data.repository.CrudRepository;
import dasz.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, String>, CustomizedSave<Message> {
}
