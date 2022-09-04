package br.com.user.api.controller;

import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceConverter;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.usecase.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final ValidateCpfUseCase validateCpfUseCase;
    private final UserResourceToUserConverter userResourceToUserConverter;
    private final UserToUserResourceConverter userToUserResourceConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register user")
    public void save(@RequestBody @Valid final UserResource userResource){
        validateCpfUseCase.execute(userResource.getCpf());
        saveUserUseCase.execute(
                userResourceToUserConverter.convert(userResource));
    }

    @PutMapping
    @ApiOperation("Update user")
    public void update(@RequestBody @Valid final UserResource userResource){
        validateCpfUseCase.execute(userResource.getCpf());
        updateUserUseCase.execute(
                userResourceToUserConverter.convert(userResource));
    }

    @DeleteMapping("/{cpf}")
    @ApiOperation("Delete user by CPF")
    public void delete(@PathVariable final String cpf){
        validateCpfUseCase.execute(cpf);
        deleteUserUseCase.execute(cpf);
    }

    @GetMapping
    @ApiOperation("Find all users")
    public List<UserResource> findAll(){
        return findAllUserUseCase.execute()
                .stream()
                .map(userToUserResourceConverter::convert)
                .collect(toList());
    }

}

