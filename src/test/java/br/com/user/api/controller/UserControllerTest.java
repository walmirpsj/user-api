package br.com.user.api.controller;

import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.build.UserResourceDataTestBuilder;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.usecase.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static br.com.user.api.build.ExampleType.VALID;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SaveUserUseCase saveUserUseCase;
    @MockBean
    private UpdateUserUseCase updateUserUseCase;
    @MockBean
    private DeleteUserUseCase deleteUserUseCase;
    @MockBean
    private FindAllUserUseCase findAllUserUseCase;
    @MockBean
    private ValidateCpfUseCase validateCpfUseCase;

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @ParameterizedTest
    @MethodSource("exampleUserResourceValid")
    @WithMockUser
    void shouldSaveUser(UserResource userResource) throws Exception {
        String writeValueAsString = objectMapper.writeValueAsString(userResource);

        this.mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("exampleUserResourceValid")
    @WithMockUser
    void shouldUpdateUser(UserResource userResource) throws Exception {
        String writeValueAsString = objectMapper.writeValueAsString(userResource);

        this.mockMvc.perform(put("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteUsers() throws Exception {
        this.mockMvc.perform(delete("/users/{cpf}", "123")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindAllUsers() throws Exception {
        Mockito.when(findAllUserUseCase.execute()).thenReturn(UserDataTestBuilder.get(3, VALID));
        this.mockMvc.perform(get("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> exampleUserResourceValid() {
        final var example = UserResourceDataTestBuilder.get(VALID);
        return Stream.of(Arguments.of(example));
    }
}
