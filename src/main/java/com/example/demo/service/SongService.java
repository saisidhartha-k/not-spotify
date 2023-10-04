package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Song;
import com.example.demo.repository.SongRepository;

@Service
public class SongService {
    
    @Autowired 
    private SongRepository songRepository;

    public Song addSong(Song song)
    {
        return songRepository.save(song);
    }

    public List<Song> getSongs()
    {
        return songRepository.findAll();
    }

    public List<Song> findByGenre(String genre)
    {
        return songRepository.findByGenre(genre);
    }

}
