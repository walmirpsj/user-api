package br.com.user.api.usecase;

import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.DeleteUserUseCaseImpl;
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
public class DeleteUserUseCaseImplTest {

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;
    @Mock
    private UserGateway userGateway;

    private static final String CPF = "99999999999";

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @Test
    public void shouldExecuteDeleteUser(){

        when(userGateway.findByCpf(anyString())).thenReturn(UserDataTestBuilder.getOptional(VALID));
        doNothing().when(userGateway).delete(any(User.class));

        deleteUserUseCase.execute(CPF);

        verify(userGateway, atLeastOnce()).findByCpf(anyString());
        verify(userGateway, atLeastOnce()).delete(any(User.class));
    }

    @Test
    public void shouldThrowExceptionToDeleteUser(){
        final var exception = assertThrows(HttpClientErrorException.class,
                () -> deleteUserUseCase.execute(CPF));
        assertNotNull(exception, exception.getMessage());
    }

}
