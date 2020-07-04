package br.com.user.api.userapi.build.repository;

import br.com.user.api.userapi.repository.model.UserDB;

public class UserDBDataTestBuilder {

    public static UserDB getUserDB1(){
        return UserDB
                .builder()
                .cpf("99999999999")
                .mail("teste.teste@gmail.com")
                .name("Fulano")
                .phone("998811122")
                .build();
    }

    public static UserDB getUserDB2(){
        return getUserDB1().toBuilder()
                .cpf("1111111111111")
                .build();
    }
}
