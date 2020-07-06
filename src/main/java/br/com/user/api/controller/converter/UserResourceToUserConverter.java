package br.com.user.api.controller.converter;

import br.com.user.api.controller.resource.UserResource;
import br.com.user.api.domain.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResourceToUserConverter {

    private final ModelMapper modelMapper;

    public User convert(UserResource userResource){
        return modelMapper.map(userResource, User.class);
    }
}
