package br.com.user.api.userapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String cpf;
    private String mail;
    private String phone;
}
