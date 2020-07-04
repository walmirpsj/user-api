package br.com.user.api.userapi.repository.impl;

import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.repository.UserRepository;
import br.com.user.api.userapi.repository.converter.UserDBToUserConverter;
import br.com.user.api.userapi.repository.converter.UserToUserDBConverter;
import br.com.user.api.userapi.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserToUserDBConverter userToUserDBConverter;
    private final UserDBToUserConverter userDBToUserConverter;

    @Override
    public User save(User user) {
        return ofNullable(user)
                .map(userToUserDBConverter::convert)
                .map(userRepository::save)
                .map(userDBToUserConverter::convert)
                .orElseGet(User::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userDBToUserConverter::convert)
                .collect(toList());
    }
}
