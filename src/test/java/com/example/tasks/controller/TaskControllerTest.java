package com.example.tasks.controller;

import com.example.tasks.controller.model.TaskCreate;
import com.example.tasks.controller.model.TaskUpdate;
import com.example.tasks.controller.model.TaskView;
import com.example.tasks.service.TaskService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TaskControllerTest {
    @Test
    void testCreate() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        TaskCreate task = TaskCreate.builder()
                .description("task")
                .build();
        taskController.create(task);

        then(taskService).should().create(task);
    }

    @Test
    void testList() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        List<TaskView> expected = Collections.singletonList(TaskView
                .builder()
                .id("1")
                .description("Task")
                .build());
        given(taskService.list()).willReturn(expected);

        List<TaskView> list = taskController.list();
        assertEquals(expected, list);

    }

    @Test
    void testUpdate() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        TaskUpdate task = TaskUpdate.builder()
                .description("task")
                .build();
        String id = "1";

        taskController.update(id, task);

        then(taskService).should().update(id, task);
    }

    @Test
    void testDelete() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        String id = "1";
        taskController.delete(id);

        then(taskService).should().delete(id);
    }
}