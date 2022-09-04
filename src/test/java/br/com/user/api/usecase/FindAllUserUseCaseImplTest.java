package br.com.user.api.usecase;

import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.FindAllUserUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindAllUserUseCaseImplTest {

    @InjectMocks
    private FindAllUserUseCaseImpl findUserUseCase;
    @Mock
    private UserGateway userGateway;

    @Test
    public void shouldExecuteFindAll(){
        final var user1 = UserDataTestBuilder.getUser1();
        final var user2 = UserDataTestBuilder.getUser2();

        when(userGateway.findAll()).thenReturn(List.of(user1, user2));

        final var response = findUserUseCase.execute();

        assertNotNull(response);
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(user1.getCpf(), response.stream().findFirst().get().getCpf());

        verify(userGateway, atLeastOnce()).findAll();
    }

}
