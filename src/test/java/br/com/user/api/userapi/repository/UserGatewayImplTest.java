package br.com.user.api.userapi.repository;

import br.com.user.api.userapi.build.domain.UserDataTestBuilder;
import br.com.user.api.userapi.build.repository.UserDBDataTestBuilder;
import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.repository.converter.UserDBToUserConverter;
import br.com.user.api.userapi.repository.converter.UserToUserDBConverter;
import br.com.user.api.userapi.repository.impl.UserGatewayImpl;
import br.com.user.api.userapi.repository.model.UserDB;
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

        final var response = userGateway.save(user);

        assertNotNull(response);
        assertEquals(userDB.getCpf(), response.getCpf());
        assertEquals(userDB.getMail(), response.getMail());
        assertEquals(userDB.getName(), response.getName());

        verify(userRepository, atLeastOnce()).save(any(UserDB.class));
        verify(userToUserDBConverter, atLeastOnce()).convert(any(User.class));
        verify(userDBToUserConverter, atLeastOnce()).convert(any(UserDB.class));
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

}
