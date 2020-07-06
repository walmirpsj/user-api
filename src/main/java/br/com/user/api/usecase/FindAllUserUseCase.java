package br.com.user.api.usecase;

import br.com.user.api.domain.User;

import java.util.List;

public interface FindAllUserUseCase {

    List<User> execute();
}
