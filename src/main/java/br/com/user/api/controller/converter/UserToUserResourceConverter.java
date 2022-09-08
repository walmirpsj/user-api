package br.com.user.api.controller.converter;

import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToUserResourceConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserResource convert(User user){
        return modelMapper.map(user, UserResource.class);
    }
}
