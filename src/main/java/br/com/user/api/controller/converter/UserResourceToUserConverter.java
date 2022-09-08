package br.com.user.api.controller.converter;

import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResourceToUserConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User convert(UserResource userResource){
        return modelMapper.map(userResource, User.class);
    }
}
