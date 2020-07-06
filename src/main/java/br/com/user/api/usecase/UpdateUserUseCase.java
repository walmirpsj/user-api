package br.com.user.api.usecase;

import br.com.user.api.domain.User;

public interface UpdateUserUseCase {
    void execute(User user);
}
