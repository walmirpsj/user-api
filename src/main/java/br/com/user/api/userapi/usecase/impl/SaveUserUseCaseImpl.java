package br.com.user.api.userapi.usecase.impl;

import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.usecase.SaveUserUseCase;
import br.com.user.api.userapi.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserGateway userGateway;

    @Override
    public User execute(User user) {
        return userGateway.save(user);
    }
}
