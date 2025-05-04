package br.com.user.api.controller;

import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceResponseConverter;
import br.com.user.api.controller.resource.UserResourceRequest;
import br.com.user.api.controller.resource.UserResourceResponse;
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
    public void save(@RequestBody @Valid final UserResourceRequest userResourceRequest){
        validateCpfUseCase.execute(userResourceRequest.getCpf());
        saveUserUseCase.execute(UserResourceToUserConverter.convert(userResourceRequest));
    }

    @PutMapping
    @Operation(description = "Update user")
    public void update(@RequestBody @Valid final UserResourceRequest userResourceRequest){
        validateCpfUseCase.execute(userResourceRequest.getCpf());
        updateUserUseCase.execute(
                UserResourceToUserConverter.convert(userResourceRequest));
    }

    @DeleteMapping("/{cpf}")
    @Operation(description = "Delete user by CPF")
    public void delete(@PathVariable final String cpf){
        validateCpfUseCase.execute(cpf);
        deleteUserUseCase.execute(cpf);
    }

    @GetMapping
    @Operation(description = "Find all users")
    public List<UserResourceResponse> findAll(){
        return findAllUserUseCase.execute()
                .stream()
                .map(UserToUserResourceResponseConverter::convert)
                .collect(toList());
    }

}

