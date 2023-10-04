package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Library;
import com.example.demo.entity.User;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer>{

    Library findByUser(User user);
    
}
