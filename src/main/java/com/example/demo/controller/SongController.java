package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Song;
import com.example.demo.service.SongService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

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

}
