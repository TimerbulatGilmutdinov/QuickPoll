package ru.itis.quickpoll.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;
}
