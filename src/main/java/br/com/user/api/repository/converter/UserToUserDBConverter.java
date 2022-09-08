package br.com.user.api.repository.converter;

import br.com.user.api.domain.User;
import br.com.user.api.repository.model.UserDB;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToUserDBConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDB convert(User user){
        return modelMapper.map(user, UserDB.class);
    }
}
