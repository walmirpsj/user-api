package br.com.user.api.controller;

import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceConverter;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.usecase.DeleteUserUseCase;
import br.com.user.api.usecase.FindAllUserUseCase;
import br.com.user.api.usecase.SaveUserUseCase;
import br.com.user.api.usecase.UpdateUserUseCase;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final UserResourceToUserConverter userResourceToUserConverter;
    private final UserToUserResourceConverter userToUserResourceConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register user")
    public void save(@RequestBody final UserResource userResource){
        saveUserUseCase.execute(
                userResourceToUserConverter.convert(userResource));
    }

    @PutMapping
    @ApiOperation("Update user")
    public void update(@RequestBody final UserResource userResource){
        updateUserUseCase.execute(
                userResourceToUserConverter.convert(userResource));
    }

    @DeleteMapping("/{cpf}")
    @ApiOperation("Delete user by CPF")
    public void delete(@PathVariable final String cpf){
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

