package com.popcorn.service;

import com.popcorn.entity.ToDoEntity;
import com.popcorn.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(profiles = {"unit-test"})
class ToDoServiceTest {
    @InjectMocks
    private ToDoService toDoService;

    @Mock
    private ToDoRepository toDoRepository;

    @Test
    void shouldReturnAToDoById() {
        Long tid = 101L;
        when(toDoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(
                ToDoEntity.builder()
                        .tid(tid)
                        .title("title")
                        .description("description")
                        .createdOn(LocalDateTime.now())
                        .updatedOn(LocalDateTime.now())
                        .completeBy(LocalDateTime.now())
                        .completed(true)
                        .completedOn(LocalDateTime.now())
                        .closingComments("closed")
                        .build()
        ));
        ToDoEntity toDoById = toDoService.getToDoById(tid);
        assertNotNull(toDoById);
        assertNotNull(toDoById.getTid());
        assertEquals(tid, toDoById.getTid());
    }
}