package ru.itis.quickpoll.dto.error;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String path;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
