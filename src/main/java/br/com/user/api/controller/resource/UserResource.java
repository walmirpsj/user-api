package br.com.user.api.controller.resource;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private String name;
    private String cpf;
    private String email;
    private String phone;
}
