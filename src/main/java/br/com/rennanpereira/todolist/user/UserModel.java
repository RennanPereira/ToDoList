package br.com.rennanpereira.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//UserModel = Padrão da tabela do database

@Data
@Entity(name = "tb_users") //nome da tabela
public class UserModel {

    @Id
    @GeneratedValue(generator =  "UUID")
    private UUID id;
        /*
     * string (texto)
     * Integer (num inteiros)
     * Double (num 0.0000)
     * Float (num 0.000)
     * cha (A C)
     * Date (data)
     * Void (vazio)
     */

    @Column(unique = true) //não permite usuário com o mesmo nome
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //getters e setters

    
}
