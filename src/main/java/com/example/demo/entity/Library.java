package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Create a many-to-one relationship with User
    @ManyToOne
    private User user;

    // Create a many-to-many relationship with Song
    @ManyToMany
    @JoinTable(
        name = "library_song",
        joinColumns = @JoinColumn(name = "library_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;
}
