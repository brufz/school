package br.com.devdojo.awesome.devdojospringboot.repository;

import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
