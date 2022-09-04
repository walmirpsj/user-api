package br.com.user.api.usecase.impl;

import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.SaveUserUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void execute(User user) {
        userGateway.save(user);
    }
}
