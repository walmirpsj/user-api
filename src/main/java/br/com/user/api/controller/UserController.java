package br.com.user.api.controller;

import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceConverter;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final ValidateCpfUseCase validateCpfUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Register user")
    public void save(@RequestBody @Valid final UserResource userResource){
        validateCpfUseCase.execute(userResource.getCpf());
        saveUserUseCase.execute(UserResourceToUserConverter.convert(userResource));
    }

    @PutMapping
    @Operation(description = "Update user")
    public void update(@RequestBody @Valid final UserResource userResource){
        validateCpfUseCase.execute(userResource.getCpf());
        updateUserUseCase.execute(
                UserResourceToUserConverter.convert(userResource));
    }

    @DeleteMapping("/{cpf}")
    @Operation(description = "Delete user by CPF")
    public void delete(@PathVariable final String cpf){
        validateCpfUseCase.execute(cpf);
        deleteUserUseCase.execute(cpf);
    }

    @GetMapping
    @Operation(description = "Find all users")
    public List<UserResource> findAll(){
        return findAllUserUseCase.execute()
                .stream()
                .map(UserToUserResourceConverter::convert)
                .collect(toList());
    }

}

