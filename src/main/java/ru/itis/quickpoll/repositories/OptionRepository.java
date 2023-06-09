package ru.itis.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.quickpoll.models.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
