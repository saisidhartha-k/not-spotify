package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Library;
import com.example.demo.entity.Song;
import com.example.demo.entity.User;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.SongRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LibraryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public Library createLibrary(User user, String libraryName) {
        Library library = new Library();
        library.setName(libraryName);
        library.setUser(user);
        return libraryRepository.save(library);
    }

    public boolean addSongToLibrary(int libraryId, int songId) {
        Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
        Optional<Song> songOptional = songRepository.findById(songId);

        if (libraryOptional.isPresent() && songOptional.isPresent()) {
            Library library = libraryOptional.get();
            Song song = songOptional.get();

            List<Song> songsInLibrary = library.getSongs();
            songsInLibrary.add(song);

            library.setSongs(songsInLibrary);
            libraryRepository.save(library);
            return true;
        }
        return false;
    }

    public List<Song> getAllSongsOfUser(User user) {
        // Find the user's library
        Library userLibrary = libraryRepository.findByUser(user);

        // Check if the user has a library
        if (userLibrary != null) {
            // Return the list of songs from the user's library
            return userLibrary.getSongs();
        } else {
            // Return an empty list or handle the case where the user has no library
            return Collections.emptyList();
        }
    }

}
