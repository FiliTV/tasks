package com.example.tasks.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {



    private String description;
    private Boolean valid;

    @CreationTimestamp
    Date createdAt;
    @Id
    @GeneratedValue
    private Long id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(description, that.description) && Objects.equals(valid, that.valid) && Objects.equals(createdAt, that.createdAt) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, valid, createdAt, id);
    }

}
