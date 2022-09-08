package br.com.user.api.integration.converter;

import br.com.user.api.domain.GitHubUser;
import br.com.user.api.integration.resource.GitHubUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GitHubUserResponseToGitHubUser {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static GitHubUser convert(GitHubUserResponse gitHubUserResponse){
        return modelMapper.map(gitHubUserResponse, GitHubUser.class);
    }
}
