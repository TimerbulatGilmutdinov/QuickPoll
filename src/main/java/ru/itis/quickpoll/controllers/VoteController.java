package ru.itis.quickpoll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.quickpoll.models.Vote;
import ru.itis.quickpoll.repositories.VoteRepository;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;

    @PostMapping("/polls/{id}/votes")
    public ResponseEntity<?> createVote(@PathVariable Long id, @RequestBody Vote vote){
        vote = voteRepository.save(vote);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders,
                HttpStatus.CREATED);
    }
}
