package br.com.user.api.usecase.impl;

import br.com.user.api.domain.GitHubUser;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.UpdateUserUseCase;
import br.com.user.api.usecase.gateway.GitHubGateway;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;
    private final GitHubGateway gitHubGateway;

    @Override
    public void execute(User user) {
        userGateway.findByCpf(user.getCpf())
           .ifPresentOrElse(userSaved -> {
               GitHubUser gitHubUser = nonNull(user.getGitHubLogin()) ?
                       gitHubGateway.findUserByLogin(user.getGitHubLogin()).orElse(null) : userSaved.getGitHubUser();
               final var userToUpdate = userSaved.toBuilder()
                       .cpf(user.getCpf())
                       .name(user.getName())
                       .email(user.getEmail())
                       .phone(user.getPhone())
                       .gitHubUser(gitHubUser)
                       .build();
               userGateway.save(userToUpdate);
           }, () -> {
               throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "User not found to update");
           });
    }
}
