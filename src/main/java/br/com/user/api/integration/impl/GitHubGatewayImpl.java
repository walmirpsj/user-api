package br.com.user.api.integration.impl;

import br.com.user.api.domain.GitHubUser;
import br.com.user.api.integration.GitHubUserClient;
import br.com.user.api.integration.converter.GitHubUserResponseToGitHubUser;
import br.com.user.api.usecase.gateway.GitHubGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class GitHubGatewayImpl implements GitHubGateway {

    private final GitHubUserClient gitHubUserClient;

    @Override
    public Optional<GitHubUser> findUserByLogin(String login) {
        return ofNullable(gitHubUserClient.findUser(login))
                .map(GitHubUserResponseToGitHubUser::convert);
    }
}
