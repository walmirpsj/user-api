package br.com.user.api.userapi.build.controller;

import br.com.user.api.userapi.controller.resource.UserResource;

public class UserResourceDataTestBuilder {

    public static UserResource getUserResource(){
        return UserResource
                .builder()
                .cpf("99999999999")
                .mail("teste.teste@gmail.com")
                .name("Fulano")
                .phone("998811122")
                .build();
    }
}
