package br.com.user.api.integration;

import br.com.user.api.integration.impl.GitHubGatewayImpl;
import br.com.user.api.integration.resource.GitHubUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.wildfly.common.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GitHubGatewayImplTest {

    @InjectMocks
    private GitHubGatewayImpl gitHubGateway;
    @Mock
    private GitHubUserClient gitHubUserClient;

    @Test
    void shouldFindUserByLogin(){
        GitHubUserResponse gitHubUserResponse = GitHubUserResponse.builder()
                .id("123456")
                .login("teste")
                .bio("Bio Teste")
                .build();
        when(gitHubUserClient.findUser(anyString())).thenReturn(gitHubUserResponse);
        final var result = gitHubGateway.findUserByLogin("teste");

        assertNotNull(result);
        verify(gitHubUserClient).findUser(anyString());
    }
}
