package br.com.user.api.repository;

import br.com.user.api.repository.model.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDB, String> {

    UserDB findByCpf(String cpf);
}
