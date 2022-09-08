package br.com.user.api.repository.impl;

import br.com.user.api.domain.User;
import br.com.user.api.repository.UserRepository;
import br.com.user.api.repository.converter.UserDBToUserConverter;
import br.com.user.api.repository.converter.UserToUserDBConverter;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(UserToUserDBConverter.convert(user));
    }

    @Override
    public void delete(User user) {
        userRepository.delete(UserToUserDBConverter.convert(user));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDBToUserConverter::convert)
                .collect(toList());
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return ofNullable(userRepository.findByCpf(cpf))
                .map(UserDBToUserConverter::convert);
    }
}
