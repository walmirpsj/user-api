package br.com.user.api.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
}
