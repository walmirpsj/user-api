package br.com.user.api.usecase.impl;

import br.com.user.api.usecase.DeleteUserUseCase;
import br.com.user.api.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void execute(String cpf) {
        userGateway.findByCpf(cpf)
                .ifPresentOrElse(userGateway::delete, this::getExceptionUserNotFound);
    }

    private void getExceptionUserNotFound() {
        throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "User not found to delete");
    }
}
