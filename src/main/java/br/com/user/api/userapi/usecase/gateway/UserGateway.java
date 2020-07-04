package br.com.user.api.userapi.usecase.gateway;

import br.com.user.api.userapi.domain.User;

import java.util.List;

public interface UserGateway {

    User save(User user);
    List<User> findAll();
}
