package br.com.rennanpereira.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
//interface= liga ponto A ao ponto B.
//liga o jpaRepository à entidade (UserModel)