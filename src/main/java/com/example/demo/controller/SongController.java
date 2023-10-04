package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.LibraryRequest;

import com.example.demo.entity.Library;
import com.example.demo.entity.Song;
import com.example.demo.entity.User;
import com.example.demo.service.LibraryService;
import com.example.demo.service.SongService;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @PostMapping("/addsong")
    public Song postMethodName(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @GetMapping("/getsongs")
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/genre/{genre}")
    public List<Song> getgenre(@PathVariable String genre)
    {
        return songService.findByGenre(genre);
    }


    @PostMapping("/createUser")
    public User creatUser(@RequestBody User user)
    {
        return userService.creatUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers()
    {
        System.out.println(userService.getusers());
        return userService.getusers();
    }

       @PostMapping("/createlib/{id}")
    public ResponseEntity<Library> createLibrary( @RequestBody LibraryRequest libraryRequest, @PathVariable int id) {
        User user = userService.findById(id);
        Library createdLibrary = libraryService.createLibrary(user, libraryRequest.getLibraryName());
        return new ResponseEntity<>(createdLibrary, HttpStatus.CREATED);
    }

    @PostMapping("/{libraryId}/add-song/{id}")
    public ResponseEntity<String> addSongToLibrary(
            @PathVariable int libraryId,
            @PathVariable int id) {
        boolean added = libraryService.addSongToLibrary(libraryId, id);
        if (added) {
            return new ResponseEntity<>("Song added to library successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Library or song not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/songs")
    public List<Song> getAllSongsOfUser(@PathVariable("userId") int userId) {
        User user = userService.findById(userId);

        if (user != null) {
            return libraryService.getAllSongsOfUser(user);
        } else {
           
            return Collections.emptyList();
        }
    }

}
