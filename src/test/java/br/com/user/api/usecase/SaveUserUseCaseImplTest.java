package br.com.user.api.usecase;

import br.com.user.api.build.GitHubUserDataTestBuilder;
import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.GitHubGateway;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.SaveUserUseCaseImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static br.com.user.api.build.ExampleType.VALID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveUserUseCaseImplTest {

    @InjectMocks
    private SaveUserUseCaseImpl saveUserUseCase;
    @Mock
    private UserGateway userGateway;
    @Mock
    private GitHubGateway gitHubGateway;

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @Test
    public void shouldExecuteSaveUser(){
        final var user = UserDataTestBuilder.get(VALID);

        when(gitHubGateway.findUserByLogin(anyString())).thenReturn(GitHubUserDataTestBuilder.getOptional(VALID));

        saveUserUseCase.execute(user);

        verify(userGateway, atLeastOnce()).save(any(User.class));
    }

    @Test
    public void shouldThrowExceptionToTrySaveUser(){
        User user = UserDataTestBuilder.get(VALID);
        when(userGateway.findByCpf(anyString())).thenReturn(Optional.of(user));

        final var exception = assertThrows(
                HttpClientErrorException.class,
                () -> saveUserUseCase.execute(user));
        assertNotNull(exception, exception.getMessage());
    }

}
