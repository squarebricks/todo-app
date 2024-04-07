package com.popcorn.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ToDoSearchFilters {
    private String title;
    private String description;

    private Integer page = 0;
    private Integer size = 10;
    private String[] sortBy = {"updatedOn", "tid"};
    private String sortDirection = "DESC";
}
