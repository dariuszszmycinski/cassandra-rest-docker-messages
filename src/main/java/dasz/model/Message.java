package dasz.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Table
public class Message {
    @PrimaryKey
    private @NonNull String id;
    private @NonNull String email;
    private @NonNull String title;
    private @NonNull String content;
    private @NonNull String magic_number;
}
