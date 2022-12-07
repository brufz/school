package br.com.devdojo.awesome.devdojospringboot.repository;

import br.com.devdojo.awesome.devdojospringboot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByLogin(String login);
}
