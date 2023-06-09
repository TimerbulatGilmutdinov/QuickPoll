package ru.itis.quickpoll.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;
}
