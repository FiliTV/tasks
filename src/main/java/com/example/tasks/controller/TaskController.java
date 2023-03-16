package com.example.tasks.controller;

import com.example.tasks.controller.model.TaskCreate;
import com.example.tasks.controller.model.TaskUpdate;
import com.example.tasks.controller.model.TaskView;
import com.example.tasks.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
public class TaskController {
    TaskService taskService;

    @CrossOrigin
    @PostMapping("/tasks")
    public void create(@RequestBody TaskCreate task) {

        TaskValidator.validateDescription(task.getDescription());

        taskService.create(task);
    }

    @CrossOrigin
    @GetMapping("/tasks")
    public List<TaskView> list() {
        return taskService.list();
    }

    @CrossOrigin
    @PutMapping(value = "/tasks/{id}")
    public void update(@PathVariable final String id, @RequestBody TaskUpdate task) {

        TaskValidator.validateDescription(task.getDescription());

        taskService.update(id, task);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public void delete(@PathVariable final String id) {
        taskService.delete(id);
    }
}
