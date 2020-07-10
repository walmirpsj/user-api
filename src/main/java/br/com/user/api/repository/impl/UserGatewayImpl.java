package br.com.user.api.repository.impl;

import br.com.user.api.domain.User;
import br.com.user.api.repository.UserRepository;
import br.com.user.api.repository.converter.UserDBToUserConverter;
import br.com.user.api.repository.converter.UserToUserDBConverter;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserToUserDBConverter userToUserDBConverter;
    private final UserDBToUserConverter userDBToUserConverter;

    @Override
    public void save(User user) {
        ofNullable(user)
                .map(userToUserDBConverter::convert)
                .map(userRepository::save);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(userToUserDBConverter.convert(user));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userDBToUserConverter::convert)
                .collect(toList());
    }

    public List<User> findByFilters(User user) {
        return userRepository.findByNameOrCpfOrEmailOrPhone(
                user.getName(), user.getCpf(), user.getEmail(), user.getPhone())
                .stream()
                .map(userDBToUserConverter::convert)
                .collect(toList());
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return ofNullable(userRepository.findByCpf(cpf))
                .map(userDBToUserConverter::convert);
    }
}
