package ru.itis.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.quickpoll.models.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
