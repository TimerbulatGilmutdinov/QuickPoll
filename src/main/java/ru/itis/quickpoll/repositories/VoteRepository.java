package ru.itis.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.quickpoll.models.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
