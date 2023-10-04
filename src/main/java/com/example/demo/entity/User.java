package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;

    // Create a one-to-many relationship with Library
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   // private List<Library> libraries;
}
