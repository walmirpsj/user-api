package br.com.user.api.build.domain;

import br.com.user.api.domain.User;

public class UserDataTestBuilder {

    public static User getUser1(){
        return User
                .builder()
                .cpf("99999999999")
                .email("teste.teste@gmail.com")
                .name("Fulano")
                .phone("998811122")
                .build();
    }

    public static User getUser2(){
        return getUser1().toBuilder()
                .cpf("1111111111111")
                .build();
    }

    public static User getUserResponse(){
        return User
                .builder()
                .id("123abc")
                .cpf("99999999999")
                .email("teste.teste@gmail.com")
                .name("Fulano")
                .phone("998811122")
                .build();
    }
}
