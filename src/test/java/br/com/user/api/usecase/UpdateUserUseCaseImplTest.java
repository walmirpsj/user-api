package br.com.user.api.usecase;

import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.GitHubGateway;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.UpdateUserUseCaseImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import static br.com.user.api.build.ExampleType.VALID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseImplTest {

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;
    @Mock
    private UserGateway userGateway;
    @Mock
    private GitHubGateway gitHubGateway;

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @Test
    public void shouldExecuteUpdateUser(){
        when(userGateway.findByCpf(anyString())).thenReturn(UserDataTestBuilder.getOptional(VALID));
        doNothing().when(userGateway).save(any(User.class));

        updateUserUseCase.execute(UserDataTestBuilder.get(VALID));

        verify(userGateway, atLeastOnce()).save(any(User.class));
        verify(gitHubGateway, atLeastOnce()).findUserByLogin(anyString());
    }

    @Test
    public void shouldThrowExceptionToUpdateUserNotFound(){
        final var user = UserDataTestBuilder.get(VALID);
        final var exception = assertThrows(
                HttpClientErrorException.class,
                () -> updateUserUseCase.execute(user));
        assertNotNull(exception, exception.getMessage());
    }
}
