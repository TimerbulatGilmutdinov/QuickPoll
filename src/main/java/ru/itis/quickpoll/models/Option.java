package ru.itis.quickpoll.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    @Id
    @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;
    @Column(name="OPTION_VALUE")
    private String value;
}
