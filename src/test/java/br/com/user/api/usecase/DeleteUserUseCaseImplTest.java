package br.com.user.api.usecase;

import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.gateway.UserGateway;
import br.com.user.api.usecase.impl.DeleteUserUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserUseCaseImplTest {

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;
    @Mock
    private UserGateway userGateway;

    private static final String CPF = "99999999999";

    @Test
    public void shouldExecuteDeleteUser(){
        final var userResponse = of(UserDataTestBuilder.getUserResponse());

        when(userGateway.findByCpf(anyString())).thenReturn(userResponse);
        doNothing().when(userGateway).delete(any(User.class));

        deleteUserUseCase.execute(CPF);

        verify(userGateway, atLeastOnce()).findByCpf(anyString());
        verify(userGateway, atLeastOnce()).delete(any(User.class));
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldThrowExceptionToDeleteUser(){
        deleteUserUseCase.execute(CPF);
    }

}
