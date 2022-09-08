package br.com.user.api.usecase.impl;

import br.com.user.api.domain.User;
import br.com.user.api.usecase.SaveUserUseCase;
import br.com.user.api.usecase.gateway.GitHubGateway;
import br.com.user.api.usecase.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserGateway userGateway;
    private final GitHubGateway gitHubGateway;

    @Override
    public void execute(User user) {
        userGateway.findByCpf(user.getCpf())
                .ifPresentOrElse(userToSave -> {
                    throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "User already existing");
                },
                () -> {
                    if(Objects.nonNull(user.getGitHubLogin())){
                        final var gitHubUser = gitHubGateway.findUserByLogin(user.getGitHubLogin());
                        final var userWithGitHub = user.toBuilder()
                                .gitHubUser(gitHubUser.orElse(null)).build();
                        userGateway.save(userWithGitHub);
                    }else{
                        userGateway.save(user);
                    }
                });
    }

}
