package br.com.user.api.userapi.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private String name;
    private String cpf;
    private String mail;
    private String phone;
}
