package br.com.rennanpereira.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


//UserController = Gerenciador de rotas.

/*
 * Modificador
 * Public
 * Private
 * Protected
 */

@RestController //faz tudo funcionar
@RequestMapping("/users") //path
public class UserController {


     //faz o spring rodar esse repositório
     @Autowired
     //Chamando o repositório
     private IUserRepository userRepository;
    
     //PostMapping (POST é o method) ("/") é o query
     @PostMapping("/")
     public ResponseEntity create(@RequestBody UserModel userModel){

        var user = this.userRepository.findByUsername(userModel.getUsername());
       
        if(user != null){
            //ResponseEntity põe os status code(200,300,400,500,600)
            //HttpStatus facilita o uso dos status code, pq da pra escrever o nome ao inver do código.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);

        //System.out.println(userModel.getUsername());
        //console.log(userModel.name)
     }
}
