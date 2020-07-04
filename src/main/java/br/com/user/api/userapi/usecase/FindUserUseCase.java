package br.com.user.api.userapi.usecase;

import br.com.user.api.userapi.domain.User;

import java.util.List;

public interface FindUserUseCase {

    List<User> execute();
}
