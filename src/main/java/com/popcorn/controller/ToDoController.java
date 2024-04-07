package com.popcorn.controller;

import com.popcorn.dto.request.CreateToDoRequest;
import com.popcorn.dto.request.ToDoSearchFilters;
import com.popcorn.dto.request.UpdateToDoRequest;
import com.popcorn.entity.ToDoEntity;
import com.popcorn.service.ToDoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Slf4j
public class ToDoController {
    private final ToDoService toDoService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ToDoEntity>> todos(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(toDoService.getAllToDos());
    }
    @GetMapping(path = "/{tid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ToDoEntity> todos(
            @PathVariable(name = "tid") final Long tid,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(toDoService.getToDoById(tid));
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ToDoEntity> todos(
            @RequestBody CreateToDoRequest createToDoRequest,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDoService.createToDo(createToDoRequest));
    }

    @PutMapping(
            path = "/{tid}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ToDoEntity> todos(
            @PathVariable(name = "tid") final Long tid,
            @RequestBody UpdateToDoRequest updateToDoRequest,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(toDoService.updateToDo(tid, updateToDoRequest));
    }

    @DeleteMapping(path = "/{tid}")
    public ResponseEntity<?> deleteToDo(
            @PathVariable(value = "tid") final Long tid,
            HttpServletRequest request
    ) {
        toDoService.deleteToDo(tid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping(path = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ToDoEntity>> search(
            ToDoSearchFilters filters,
            HttpServletRequest request
    ) {
        log.info("Search Filters: " + filters.toString());
        return ResponseEntity.status(HttpStatus.OK)
                .body(toDoService.search(filters));
    }
}
