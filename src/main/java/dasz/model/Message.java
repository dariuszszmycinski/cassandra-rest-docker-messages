package dasz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@Table

public class Message {
    private static int count = 0;

    @PrimaryKey
    @Id
    private int id;
    private @NonNull String email;
    private @NonNull String title;
    private @NonNull String content;
    private int magic_number;

    public Message(@NonNull String email, @NonNull String title, @NonNull String content, int magic_number) {
        this.id = ++count;
        this.email = email;
        this.title = title;
        this.content = content;
        this.magic_number = magic_number;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", magic_number=" + magic_number +
                '}';
    }
}
