package br.com.user.api.userapi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserDB {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String mail;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
