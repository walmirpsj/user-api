package br.com.user.api.usecase.gateway;

import br.com.user.api.domain.User;

import java.util.List;

public interface UserGateway {

    void save(User user);
    List<User> findAll();
    List<User> findByFilters(User user);
}
