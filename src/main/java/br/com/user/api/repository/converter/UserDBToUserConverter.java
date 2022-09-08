package br.com.user.api.repository.converter;

import br.com.user.api.domain.User;
import br.com.user.api.repository.model.UserDB;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDBToUserConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User convert(UserDB userDB){
        return modelMapper.map(userDB, User.class);
    }
}
