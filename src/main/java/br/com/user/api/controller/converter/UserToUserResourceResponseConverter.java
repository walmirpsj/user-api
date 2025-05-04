package br.com.user.api.controller.converter;

import br.com.user.api.controller.resource.UserResourceResponse;
import br.com.user.api.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToUserResourceResponseConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserResourceResponse convert(User user){
        return modelMapper.map(user, UserResourceResponse.class);
    }
}
