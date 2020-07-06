package br.com.user.api.usecase.impl;

import br.com.user.api.domain.User;
import br.com.user.api.usecase.FindAllUserUseCase;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllUserUseCaseImpl implements FindAllUserUseCase {

    private final UserGateway userGateway;

    @Override
    public List<User> execute() {
        return userGateway.findAll();
    }
}
