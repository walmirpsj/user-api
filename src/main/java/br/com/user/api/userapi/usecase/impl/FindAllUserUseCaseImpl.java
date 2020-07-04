package br.com.user.api.userapi.usecase.impl;

import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.usecase.FindUserUseCase;
import br.com.user.api.userapi.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllUserUseCaseImpl implements FindUserUseCase {

    private final UserGateway userGateway;

    @Override
    public List<User> execute() {
        return userGateway.findAll();
    }
}
