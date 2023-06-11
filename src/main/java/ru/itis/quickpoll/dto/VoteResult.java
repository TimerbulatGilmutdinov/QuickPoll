package ru.itis.quickpoll.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> results;
}
