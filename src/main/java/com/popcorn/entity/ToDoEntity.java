package com.popcorn.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(
        name = "TODO_ENTITY"
)
@Table(
        name = "todos"
)
@EntityListeners(AuditingEntityListener.class)
public class ToDoEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6849713093754667710L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tid;

    private String title;
    private String description;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdOn;
    @LastModifiedDate
    @Column(
            insertable = false
    )
    private LocalDateTime updatedOn;

    private LocalDateTime completeBy;
    private Boolean completed;
    private LocalDateTime completedOn;

    private String closingComments;
}
