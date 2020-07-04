package br.com.user.api.userapi.repository.converter;

import br.com.user.api.userapi.domain.User;
import br.com.user.api.userapi.repository.model.UserDB;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDBToUserConverter {

    private final ModelMapper modelMapper;

    public User convert(UserDB userDB){
        return modelMapper.map(userDB, User.class);
    }
}
