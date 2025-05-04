package br.com.user.api.controller.resource;

import br.com.user.api.domain.GitHubUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResourceResponse {
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private GitHubUser gitHubUser;

}
