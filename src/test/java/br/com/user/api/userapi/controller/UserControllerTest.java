package br.com.user.api.userapi.controller;

import br.com.user.api.userapi.build.controller.UserResourceDataTestBuilder;
import br.com.user.api.userapi.build.domain.UserDataTestBuilder;
import br.com.user.api.userapi.controller.converter.UserResourceToUserConverter;
import br.com.user.api.userapi.controller.converter.UserToUserResourceConverter;
import br.com.user.api.userapi.controller.resource.UserResource;
import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.usecase.FindUserUseCase;
import br.com.user.api.userapi.usecase.SaveUserUseCase;
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
    private FindUserUseCase findUserUseCase;
    @Spy
    private UserResourceToUserConverter userResourceToUserConverter
            = new UserResourceToUserConverter(new ModelMapper());
    @Spy
    private UserToUserResourceConverter userToUserResourceConverter
            = new UserToUserResourceConverter(new ModelMapper());

    @Test
    public void shoulSaveUser(){
        final var userResponse = UserDataTestBuilder.getUser1();
        final var userRequest = UserResourceDataTestBuilder.getUserResource();

        when(saveUserUseCase.execute(any(User.class))).thenReturn(userResponse);

        final var response = userController.save(userRequest);

        assertNotNull(response);
        assertEquals(userResponse.getCpf(), response.getCpf());
        assertEquals(userResponse.getMail(), response.getMail());
        assertEquals(userResponse.getName(), response.getName());

        verify(saveUserUseCase, atLeastOnce()).execute(any(User.class));
        verify(userResourceToUserConverter, atLeastOnce()).convert(any(UserResource.class));
        verify(userToUserResourceConverter, atLeastOnce()).convert(any(User.class));
    }

    @Test
    public void shouldFindAll(){
        final var userResponse1 = UserDataTestBuilder.getUser1();
        final var userResponse2 = UserDataTestBuilder.getUser2();

        when(findUserUseCase.execute()).thenReturn(List.of(userResponse1, userResponse2));

        final var response = userController.findAll();

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(userResponse1.getCpf(), response.stream().findFirst().get().getCpf());

        verify(findUserUseCase, atLeastOnce()).execute();
        verify(userToUserResourceConverter, atLeastOnce()).convert(any(User.class));
    }
}
