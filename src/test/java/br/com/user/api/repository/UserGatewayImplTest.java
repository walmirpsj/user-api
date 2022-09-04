package br.com.user.api.repository;

import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.build.repository.UserDBDataTestBuilder;
import br.com.user.api.domain.User;
import br.com.user.api.repository.converter.UserDBToUserConverter;
import br.com.user.api.repository.converter.UserToUserDBConverter;
import br.com.user.api.repository.impl.UserGatewayImpl;
import br.com.user.api.repository.model.UserDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl userGateway;
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserToUserDBConverter userToUserDBConverter
            = new UserToUserDBConverter(new ModelMapper());
    @Spy
    private UserDBToUserConverter userDBToUserConverter
            = new UserDBToUserConverter(new ModelMapper());

    @Test
    public void shouldSaveUser(){
        final var userDB = UserDBDataTestBuilder.getUserDB1();

        when(userRepository.save(any(UserDB.class))).thenReturn(userDB);

        final var user =  UserDataTestBuilder.getUser1();

        userGateway.save(user);

        verify(userRepository, atLeastOnce()).save(any(UserDB.class));
        verify(userToUserDBConverter, atLeastOnce()).convert(any(User.class));
    }

    @Test
    public void shouldDeleteUser(){
        doNothing().when(userRepository).delete(any(UserDB.class));

        final var user =  UserDataTestBuilder.getUser1();

        userGateway.delete(user);

        verify(userRepository, atLeastOnce()).delete(any(UserDB.class));
        verify(userToUserDBConverter, atLeastOnce()).convert(any(User.class));
    }

    @Test
    public void shouldFindAll(){
        final var userDB1 = UserDBDataTestBuilder.getUserDB1();
        final var userDB2 = UserDBDataTestBuilder.getUserDB2();

        when(userRepository.findAll()).thenReturn(List.of(userDB1, userDB2));

        final var response = userGateway.findAll();

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(userDB1.getCpf(), response.stream().findFirst().get().getCpf());

        verify(userRepository, atLeastOnce()).findAll();
        verify(userDBToUserConverter, atLeastOnce()).convert(any(UserDB.class));
    }

    @Test
    public void shouldFindByCpf(){
        final var userDB = UserDBDataTestBuilder.getUserDB1();

        when(userRepository.findByCpf(anyString())).thenReturn(userDB);

        final var response = userGateway.findByCpf("99999999999");

        assertNotNull(response);
        assertEquals(userDB.getCpf(), response.get().getCpf());
        assertEquals(userDB.getName(), response.get().getName());
        assertEquals(userDB.getEmail(), response.get().getEmail());

        verify(userRepository, atLeastOnce()).findByCpf(anyString());
        verify(userDBToUserConverter, atLeastOnce()).convert(any(UserDB.class));
    }

}
