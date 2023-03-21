package com.funda.springboot.fundament.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//creamos entidad user
@Entity
@Table(name = "user")
public class User {
    //agregamos un id para esa identidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//se auto generar un numero cada que tengamos un registro
    @Column(name = "id_user", nullable = false, unique = true)//nombre columna a nivel de base de datos
    private Long id;
    //tambien agregamos las siguientes columnas con un length de 50
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
//agreagamos la propiedad birthdate
    private LocalDate birthDate;
    //hacemos relacion con otra identidad oneToMany
    //significa que el usuario va tener muchos post, muchos post para un solo usuario
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();
//constructor
    public User() {
    }
//constructor
    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
//metodos geterr y seteer
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }
}
