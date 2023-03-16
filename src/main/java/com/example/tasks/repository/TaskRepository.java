package com.example.tasks.repository;

import com.example.tasks.repository.model.TaskEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity,Long> {
    @Modifying
    @Query("update TaskEntity t set t.description = :description where t.id = :id")
    void updateDescription(@Param("id") String id, @Param("description") String description);

    @Modifying
    @Query("update TaskEntity t set t.valid = :valid where t.id = :id")
    void updateValid(@Param("id") String id, @Param("valid") Boolean valid);
}
