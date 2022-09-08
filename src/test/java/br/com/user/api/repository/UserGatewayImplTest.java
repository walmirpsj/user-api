package br.com.user.api.repository;

import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.build.UserDBDataTestBuilder;
import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.repository.impl.UserGatewayImpl;
import br.com.user.api.repository.model.UserDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.user.api.build.ExampleType.VALID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl userGateway;
    @Mock
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @Test
    public void shouldSaveUser(){
        final var userDB = UserDBDataTestBuilder.get(VALID);

        when(userRepository.save(any(UserDB.class))).thenReturn(userDB);

        final var user =  UserDataTestBuilder.get(VALID);

        userGateway.save(user);

        verify(userRepository, atLeastOnce()).save(any(UserDB.class));
    }

    @Test
    public void shouldDeleteUser(){
        doNothing().when(userRepository).delete(any(UserDB.class));

        final var user =  UserDataTestBuilder.get(VALID);

        userGateway.delete(user);

        verify(userRepository, atLeastOnce()).delete(any(UserDB.class));
    }

    @Test
    public void shouldFindAll(){

        when(userRepository.findAll()).thenReturn(UserDBDataTestBuilder.get(2, VALID));

        final var response = userGateway.findAll();

        assertNotNull(response);
        assertTrue(response.size() > 0);

        verify(userRepository, atLeastOnce()).findAll();
    }

    @Test
    public void shouldFindByCpf(){
        final var userDB = UserDBDataTestBuilder.get(VALID);

        when(userRepository.findByCpf(anyString())).thenReturn(userDB);

        final var response = userGateway.findByCpf("99999999999");

        assertNotNull(response);
        assertEquals(userDB.getCpf(), response.get().getCpf());
        assertEquals(userDB.getName(), response.get().getName());
        assertEquals(userDB.getEmail(), response.get().getEmail());

        verify(userRepository, atLeastOnce()).findByCpf(anyString());
    }

}
