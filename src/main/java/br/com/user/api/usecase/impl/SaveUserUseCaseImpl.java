package br.com.user.api.usecase.impl;

import br.com.user.api.domain.User;
import br.com.user.api.usecase.SaveUserUseCase;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@AllArgsConstructor
public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void execute(User user) {
        userGateway.findByCpf(user.getCpf())
                .ifPresentOrElse(userToSave -> getExceptionUserAlreadyExisting(),
                        () -> userGateway.save(user));
    }

    private void getExceptionUserAlreadyExisting() {
        throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "User already existing");
    }
}
