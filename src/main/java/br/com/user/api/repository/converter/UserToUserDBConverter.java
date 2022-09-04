package br.com.user.api.repository.converter;

import br.com.user.api.domain.User;
import br.com.user.api.repository.model.UserDB;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserDBConverter {

    private final ModelMapper modelMapper;

    public UserDB convert(User user){
        return modelMapper.map(user, UserDB.class);
    }
}
