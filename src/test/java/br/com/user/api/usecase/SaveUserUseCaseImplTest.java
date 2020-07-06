package br.com.user.api.usecase;

import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.SaveUserUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        doNothing().when(userGateway).save(any(User.class));

        saveUserUseCase.execute(user);

        verify(userGateway, atLeastOnce()).save(any(User.class));
    }

}
