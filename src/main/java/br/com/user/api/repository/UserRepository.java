package br.com.user.api.repository;

import br.com.user.api.repository.model.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserDB, String> {

    List<UserDB> findByNameOrCpfOrEmailOrPhone(String name, String cpf, String email, String phone);
    UserDB findByCpf(String cpf);
}
