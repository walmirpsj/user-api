package br.com.user.api.usecase;

import br.com.user.api.domain.User;

public interface SaveUserUseCase {

    void execute(User user);
}
