package br.com.user.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String gitHubLogin;
    private GitHubUser gitHubUser;

}
