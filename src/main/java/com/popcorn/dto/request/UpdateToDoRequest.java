package com.popcorn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToDoRequest {
    private String title;
    private String description;

    private LocalDateTime completeBy;
    private Boolean completed;
    private LocalDateTime completedOn;

    private String closingComments;
}
