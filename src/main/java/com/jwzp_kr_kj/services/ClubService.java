package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Club;
import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.repos.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    public ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    public void addClub(Club club) {
        clubRepository.save(club);
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club> getClub(int id) {
        return clubRepository.findById(id);
    }

    public ResponseEntity<Object> deleteClub(int id) {
        Optional<Club> club = getClub(id);
        if (club.isPresent()) {
            clubRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
