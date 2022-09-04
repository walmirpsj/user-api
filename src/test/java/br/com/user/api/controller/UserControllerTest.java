package br.com.user.api.controller;

import br.com.user.api.build.controller.UserResourceDataTestBuilder;
import br.com.user.api.build.domain.UserDataTestBuilder;
import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceConverter;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.domain.User;
import br.com.user.api.usecase.*;
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
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private SaveUserUseCase saveUserUseCase;
    @Mock
    private UpdateUserUseCase updateUserUseCase;
    @Mock
    private DeleteUserUseCase deleteUserUseCase;
    @Mock
    private FindAllUserUseCase findAllUserUseCase;
    @Mock
    private ValidateCpfUseCase validateCpfUseCase;
    @Spy
    private UserResourceToUserConverter userResourceToUserConverter
            = new UserResourceToUserConverter(new ModelMapper());
    @Spy
    private UserToUserResourceConverter userToUserResourceConverter
            = new UserToUserResourceConverter(new ModelMapper());

    @Test
    public void shoulSaveUser(){
        final var userRequest = UserResourceDataTestBuilder.getUserResource();

        doNothing().when(validateCpfUseCase).execute(anyString());
        doNothing().when(saveUserUseCase).execute(any(User.class));

        userController.save(userRequest);

        verify(saveUserUseCase, atLeastOnce()).execute(any(User.class));
        verify(userResourceToUserConverter, atLeastOnce()).convert(any(UserResource.class));
    }

    @Test
    public void shoulUpdateUser(){
        final var userRequest = UserResourceDataTestBuilder.getUserResource();

        doNothing().when(validateCpfUseCase).execute(anyString());
        doNothing().when(updateUserUseCase).execute(any(User.class));

        userController.update(userRequest);

        verify(updateUserUseCase, atLeastOnce()).execute(any(User.class));
        verify(userResourceToUserConverter, atLeastOnce()).convert(any(UserResource.class));
    }

    @Test
    public void shoulDeleteUser(){
        doNothing().when(validateCpfUseCase).execute(anyString());
        doNothing().when(deleteUserUseCase).execute(anyString());

        userController.delete("99999999999");

        verify(deleteUserUseCase, atLeastOnce()).execute(anyString());
    }

    @Test
    public void shouldFindAll(){
        final var userResponse1 = UserDataTestBuilder.getUser1();
        final var userResponse2 = UserDataTestBuilder.getUser2();

        when(findAllUserUseCase.execute()).thenReturn(List.of(userResponse1, userResponse2));

        final var response = userController.findAll();

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(userResponse1.getCpf(), response.stream().findFirst().get().getCpf());

        verify(findAllUserUseCase, atLeastOnce()).execute();
        verify(userToUserResourceConverter, atLeastOnce()).convert(any(User.class));
    }
}
