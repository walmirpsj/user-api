package br.com.user.api.userapi.controller;

import br.com.user.api.userapi.controller.converter.UserResourceToUserConverter;
import br.com.user.api.userapi.controller.converter.UserToUserResourceConverter;
import br.com.user.api.userapi.controller.resource.UserResource;
import br.com.user.api.userapi.usecase.FindUserUseCase;
import br.com.user.api.userapi.usecase.SaveUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UserResourceToUserConverter userResourceToUserConverter;
    private final UserToUserResourceConverter userToUserResourceConverter;

    @PostMapping
    public UserResource save(@RequestBody UserResource userResource){
        return ofNullable(userResource)
                .map(userResourceToUserConverter::convert)
                .map(saveUserUseCase::execute)
                .map(userToUserResourceConverter::convert)
                .orElseGet(UserResource::new);
    }

    @GetMapping
    public List<UserResource> findAll(){
        return findUserUseCase.execute()
                .stream()
                .map(userToUserResourceConverter::convert)
                .collect(toList());
    }

}

