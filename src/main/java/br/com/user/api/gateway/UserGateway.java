package br.com.user.api.gateway;

import br.com.user.api.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    void save(User user);
    void delete(User user);
    List<User> findAll();
    Optional<User> findByCpf(String cpf);
}
