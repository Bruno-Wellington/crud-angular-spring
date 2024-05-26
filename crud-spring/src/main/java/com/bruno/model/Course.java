package com.bruno.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // que importa os metodos getters setter e...
@Entity
//@Table(name = "cursos") caso queira criar a tabela com um nome diferente.
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?") //Essa anotação do hibernate fazemos que toda vez que o metodo de delete for chamado de um update no campo status sem ter que mecher muito nos codigos ja feitos.
@SQLRestriction("status = 'Ativo'")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @org.hibernate.validator.constraints.Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @org.hibernate.validator.constraints.Length(max = 10)
    @Pattern(regexp = "Back-End|Front-End")
    @Column(length = 10, nullable = false)
    private String category;

    @NotBlank
    @org.hibernate.validator.constraints.Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";
}
