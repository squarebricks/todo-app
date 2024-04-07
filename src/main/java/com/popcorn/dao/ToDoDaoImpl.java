package com.popcorn.dao;

import com.popcorn.dto.request.ToDoSearchFilters;
import com.popcorn.entity.ToDoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ToDoDaoImpl {
    private final EntityManager entityManager;
    public List<ToDoEntity> search(ToDoSearchFilters filters) {
        Map<String, Object> fields = new LinkedHashMap<>();
        StringBuilder query = new StringBuilder()
                .append("SELECT TODO FROM TODO_ENTITY TODO ")
                .append("WHERE 1=1 ");
        if(filters != null) {
            if(filters.getTitle() != null) {
                query.append("AND LOWER(TODO.title) LIKE (:title) ");
                fields.put("title", "%" + filters.getTitle().toLowerCase() + "%");
            }
            if(filters.getDescription() != null) {
                query.append("AND LOWER(TODO.description) LIKE (:description) ");
                fields.put("description", "%" + filters.getDescription().toLowerCase() + "%");
            }

            String orderBy = Stream.of(filters.getSortBy())
                    .map(s -> "TODO." + s)
                    .collect(Collectors.joining(","));

            query.append("ORDER BY ").append(orderBy).append(" ")
                    .append(filters.getSortDirection()).append(" ");

            query.append("LIMIT ").append(filters.getSize())
                    .append(" OFFSET ").append(filters.getPage() * filters.getSize());
        }
        log.info(query.toString());
        TypedQuery<ToDoEntity> typedQuery = entityManager.createQuery(query.toString(), ToDoEntity.class);
        for(Map.Entry<String, Object> entry: fields.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return typedQuery.getResultList();
    }
}
