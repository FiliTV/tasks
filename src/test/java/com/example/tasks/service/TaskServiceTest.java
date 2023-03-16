package com.example.tasks.service;

import com.example.tasks.controller.model.TaskCreate;
import com.example.tasks.controller.model.TaskUpdate;
import com.example.tasks.controller.model.TaskView;
import com.example.tasks.repository.TaskRepository;
import com.example.tasks.repository.model.TaskEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TaskServiceTest {

    @Test
    void testCreate() {

        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);
        taskService.create(TaskCreate.builder()
                .description("Task created")
                .build());

        TaskEntity entity = TaskEntity.builder()
                .description("Task created")
                .valid(true)
                .build();
        then(taskRepository).should().save(entity);
    }

    @Test
    void testList() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        List<TaskEntity> tasks = Collections.singletonList(TaskEntity.builder()
                .id(Long.valueOf(1))
                .description("Task")
                .valid(true)
                .build());
        given(taskRepository.findAll()).willReturn(tasks);

        List<TaskView> list = taskService.list();

        List<TaskView> expected = List.of(TaskView.builder()
                .id("1")
                .description("Task")
                .valid(true)
                .build());
        ;
        assertEquals(expected.get(0).getId(), list.get(0).getId());
        assertEquals(expected.get(0).getDescription(), list.get(0).getDescription());


    }

    @Test
    void testUpdate() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        taskService.update("1", TaskUpdate.builder()
                        .description("Task")
                        .valid(false)
                .build());

        then(taskRepository).should().updateDescription("1","Task");
        then(taskRepository).should().updateValid("1",false);
    }

    @Test
    void testDelete() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        taskService.delete("1");

        then(taskRepository).should().deleteById(Long.valueOf(1));

    }
}