package com.bruno.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.bruno.enums.Category;
import com.bruno.enums.Status;
import com.bruno.enums.converters.CategoryConverter;
import com.bruno.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();
}
