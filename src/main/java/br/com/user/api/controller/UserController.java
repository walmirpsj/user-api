package br.com.user.api.controller;

import br.com.user.api.controller.converter.UserResourceToUserConverter;
import br.com.user.api.controller.converter.UserToUserResourceConverter;
import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.usecase.FindAllUserUseCase;
import br.com.user.api.usecase.SaveUserUseCase;
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
    private final FindAllUserUseCase findAllUserUseCase;
    private final UserResourceToUserConverter userResourceToUserConverter;
    private final UserToUserResourceConverter userToUserResourceConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserResource userResource){
        saveUserUseCase.execute(
                userResourceToUserConverter.convert(userResource));
    }

    @PutMapping
    public void update(@RequestBody UserResource userResource){

    }

    @GetMapping
    public List<UserResource> findAll(UserResource userResource){
        return findAllUserUseCase.execute()
                .stream()
                .map(userToUserResourceConverter::convert)
                .collect(toList());
    }

}

