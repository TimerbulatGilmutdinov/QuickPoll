package ru.itis.quickpoll.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poll {
    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long id;
    @Column(name = "QUESTION")
    private String question;
    @OneToMany
    @JoinColumn(name="POLL_ID")
    @OrderBy
    private Set<Option> options;
}
