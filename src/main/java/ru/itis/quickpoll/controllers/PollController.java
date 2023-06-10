package ru.itis.quickpoll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.quickpoll.models.Option;
import ru.itis.quickpoll.models.Poll;
import ru.itis.quickpoll.repositories.PollRepository;

import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {
    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @GetMapping("/polls/{id}")
    public ResponseEntity<?> getPoll(@PathVariable Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) {
            throw new IllegalArgumentException("Poll not found");
        }
        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
    }

    @PutMapping("/polls/{id}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long id) {
        Poll newPoll = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{id}")
    public ResponseEntity<?> deletePoll(@PathVariable Long id) {
        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();

        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
