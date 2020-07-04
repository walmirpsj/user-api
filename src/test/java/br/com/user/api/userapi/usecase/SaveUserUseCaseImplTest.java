package br.com.user.api.userapi.usecase;

import br.com.user.api.userapi.build.domain.UserDataTestBuilder;
import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.usecase.gateway.UserGateway;
import br.com.user.api.userapi.usecase.impl.SaveUserUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveUserUseCaseImplTest {

    @InjectMocks
    private SaveUserUseCaseImpl saveUserUseCase;
    @Mock
    private UserGateway userGateway;

    @Test
    public void shouldExecuteSaveUser(){
        final var user = UserDataTestBuilder.getUser1();
        when(userGateway.save(any(User.class))).thenReturn(user);

        final var response = saveUserUseCase.execute(user);

        assertNotNull(response);
        assertEquals(user.getCpf(), response.getCpf());
        assertEquals(user.getMail(), response.getMail());
        assertEquals(user.getName(), response.getName());

        verify(userGateway, atLeastOnce()).save(any(User.class));
    }

}
