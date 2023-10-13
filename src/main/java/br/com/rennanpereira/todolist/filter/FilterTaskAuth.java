package br.com.rennanpereira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rennanpereira.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //esse filter só é necessário na rota de criação de tasks
                var servletPath = request.getServletPath();

                //se o path da req é "tasks" então executa o filter
                if(servletPath.startsWith("/tasks/")){

                    //Pegar a autenticação (usuário e senha)

                //pegar a autorização(usuário e senha) de dentro da request(ela vem criptografada e tudo junto)
                var authorization = request.getHeader("Authorization");

                //subtraindo caracteres desnecessarios da auth criptografada
                var authEncoded = authorization.substring("basic".length()).trim();
                
                //colocar o resultado dentro de um array, em bytes.
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                //transformar o array em string
                var authString = new String(authDecode);

                //separa o username do password, em um array
                String[] credentials = authString.split(":");

                //posição 1 do array= username posição 2= password
                String username = credentials[0];
                String password = credentials[1];
                
                System.out.println(username);
                System.out.println(password);

                //validar usuário
                //procurar no db se existe o usuário
                var user = this.userRepository.findByUsername(username);

                //se n achar, manda msg de erro,se achar segue em frente
                if(user == null){
                    response.sendError(401);
                }else{

                    //validar senha
                    //verificar se senha da req é a msm senha salva do usuário no db
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if(passwordVerify.verified){
                        request.setAttribute("idUser", user.getId());//setar o Id da req auto
                        filterChain.doFilter(request, response);
                    }else{
                        response.sendError(401);
                    }
                    //segue viagem

                    
                }

                }else{
                 filterChain.doFilter(request, response);

                }

                
        
        }            

    
}
