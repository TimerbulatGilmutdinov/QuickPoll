package ru.itis.quickpoll.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Column(name = "QUESTION")
    private String question;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6)
    private Set<Option> options;
}
