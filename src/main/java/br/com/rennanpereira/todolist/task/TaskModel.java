package br.com.rennanpereira.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

   /*
     * ID
     * Usuário (ID_USUARIO)
     * Descrição
     * Título
     * Data de inicio
     * Data de término
     * Prioridade
     */

@Data //getters e setters
@Entity(name="tb_tasks")
public class TaskModel {

     @Id
     @GeneratedValue(generator = "UUID")//gerar id auto.
     private UUID id;
     private String description;

     @Column(length = 50)//max-lengh do title
     private String title;
     private LocalDateTime startAt;
     private LocalDateTime endAt;
     private String priority;

     private UUID idUser;

     
     @CreationTimestamp //marca qdo a tarefa foi criada no db
     private LocalDateTime createdAt;

     public void setTitle(String title) throws Exception {
          if(title.length() > 50){
               throw new Exception("O campo title deve conter no máximo 50 caracteres");

          }
          this.title = title;
     }

}

/*
 * Tarefas
 * Estrutura da tabela
 */