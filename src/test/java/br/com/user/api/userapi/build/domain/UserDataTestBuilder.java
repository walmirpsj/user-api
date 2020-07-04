package br.com.user.api.userapi.build.domain;

import br.com.user.api.userapi.domain.User;

public class UserDataTestBuilder {

    public static User getUser1(){
        return User
                .builder()
                .cpf("99999999999")
                .mail("teste.teste@gmail.com")
                .name("Fulano")
                .phone("998811122")
                .build();
    }

    public static User getUser2(){
        return getUser1().toBuilder()
                .cpf("1111111111111")
                .build();
    }
}
