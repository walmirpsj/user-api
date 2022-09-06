package br.com.user.api.usecase;

import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.gateway.UserGateway;
import br.com.user.api.usecase.impl.UpdateUserUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserUseCaseImplTest {

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;
    @Mock
    private UserGateway userGateway;

    @Test
    public void shouldExecuteUpdateUser(){
        final var userResponse = Optional.of(UserDataTestBuilder.getUserResponse());
        final var userRequest = UserDataTestBuilder.getUser1();

        when(userGateway.findByCpf(anyString())).thenReturn(userResponse);
        doNothing().when(userGateway).save(any(User.class));

        updateUserUseCase.execute(userRequest);

        verify(userGateway, atLeastOnce()).save(any(User.class));
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldThrowExceptionToUpdateUserNotFound(){
        final var userRequest = UserDataTestBuilder.getUser1();
        updateUserUseCase.execute(userRequest);
    }
}
