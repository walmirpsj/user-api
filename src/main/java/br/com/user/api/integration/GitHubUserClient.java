package br.com.user.api.integration;

import br.com.user.api.integration.resource.GitHubUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github-user", url = "${api.github-user.url}")
public interface GitHubUserClient {

    @GetMapping("/users/{login}")
    GitHubUserResponse findUser(@PathVariable("login") String login);
}
