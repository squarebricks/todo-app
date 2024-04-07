package com.popcorn.service;

import com.google.gson.Gson;
import com.popcorn.dao.ToDoDaoImpl;
import com.popcorn.dto.request.CreateToDoRequest;
import com.popcorn.dto.request.ToDoSearchFilters;
import com.popcorn.dto.request.UpdateToDoRequest;
import com.popcorn.entity.ToDoEntity;
import com.popcorn.exception.custom.ToDoNotFoundException;
import com.popcorn.exception.custom.ToDoUpdateFailedException;
import com.popcorn.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ToDoService {
    private final ToDoDaoImpl toDoDao;
    private final ToDoRepository toDoRepository;
    private final Gson jsonHelper;

    public List<ToDoEntity> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDoEntity getToDoById(Long tid) throws ToDoNotFoundException {
        Optional<ToDoEntity> byIdOptional = toDoRepository.findById(tid);
        if(byIdOptional.isPresent()) {
            return byIdOptional.get();
        }
        throw new ToDoNotFoundException(
                String.format("todo with tid %d is not found", tid)
        );
    }

    public ToDoEntity createToDo(CreateToDoRequest createToDoRequest) {
        ToDoEntity toDoEntity = ToDoEntity.builder()
                .title(createToDoRequest.getTitle())
                .description(createToDoRequest.getDescription())
                .completeBy(createToDoRequest.getCompleteBy())
                .completed(false)
                .completedOn(null)
                .closingComments(null)
                .build();
        return toDoRepository.saveAndFlush(toDoEntity);
    }

    public ToDoEntity updateToDo(final Long tid, UpdateToDoRequest updateToDoRequest) {
        Optional<ToDoEntity> byIdOptional = toDoRepository.findById(tid);
        if (byIdOptional.isPresent()) {
            ToDoEntity entity = byIdOptional.get();
            if(updateToDoRequest.getTitle() != null) {
                entity.setTitle(updateToDoRequest.getTitle());
            }
            if(updateToDoRequest.getDescription() != null) {
                entity.setDescription(updateToDoRequest.getDescription());
            }
            if(updateToDoRequest.getCompleteBy() != null) {
                entity.setCompleteBy(updateToDoRequest.getCompleteBy());
            }
            if(updateToDoRequest.getCompleted() != null) {
                entity.setCompleted(updateToDoRequest.getCompleted());
            }
            if(updateToDoRequest.getCompletedOn() != null) {
                entity.setCompletedOn(updateToDoRequest.getCompletedOn());
            }
            if(updateToDoRequest.getClosingComments() != null) {
                entity.setClosingComments(updateToDoRequest.getClosingComments());
            }
            return toDoRepository.saveAndFlush(entity);
        }
        throw new ToDoUpdateFailedException(String.format("no todo found with tid %d to update", tid));
    }

    public void deleteToDo(final Long tid) {
        log.warn(String.format("deleting todo with tid %d", tid));
        toDoRepository.deleteById(tid);
    }

    public List<ToDoEntity> search(ToDoSearchFilters filters) {
        return toDoDao.search(filters);
    }
}
