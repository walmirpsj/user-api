package br.com.user.api.userapi.controller.converter;

import br.com.user.api.userapi.controller.resource.UserResource;
import br.com.user.api.userapi.domain.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserToUserResourceConverter {

    private final ModelMapper modelMapper;

    public UserResource convert(User user){
        return modelMapper.map(user, UserResource.class);
    }
}
