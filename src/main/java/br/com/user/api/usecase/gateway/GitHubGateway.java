package br.com.user.api.usecase.gateway;

import br.com.user.api.domain.GitHubUser;

import java.util.Optional;

public interface GitHubGateway {

    Optional<GitHubUser> findUserByLogin(String login);
}
