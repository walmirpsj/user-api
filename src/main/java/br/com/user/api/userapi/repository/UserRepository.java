package br.com.user.api.userapi.repository;

import br.com.user.api.userapi.repository.model.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDB, String> {
}
