package br.com.user.api.userapi.usecase;

import br.com.user.api.userapi.domain.User;

public interface SaveUserUseCase {

    User execute(User user);
}
