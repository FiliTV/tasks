package com.example.tasks.service;

import com.example.tasks.controller.model.TaskCreate;
import com.example.tasks.controller.model.TaskUpdate;
import com.example.tasks.controller.model.TaskView;
import com.example.tasks.repository.TaskRepository;
import com.example.tasks.repository.model.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {


    private TaskRepository taskRepository;
    public void create(TaskCreate task) {
        taskRepository.save(TaskEntity.builder()
                        .description(task.getDescription())
                        .valid(true)
                .build());
    }

    public List<TaskView> list() {
        List<TaskEntity> all = (List<TaskEntity>) taskRepository.findAll();
        return all.stream().map( t -> TaskView.builder()
                .id(String.valueOf(t.getId()))
                .description(t.getDescription())
                .valid(t.getValid())
                .created(t.getCreatedAt())
                .build()).collect(Collectors.toList());
    }
    @Transactional
    public void update(String id, TaskUpdate task) {
        taskRepository.updateDescription(id, task.getDescription());
        taskRepository.updateValid(id, task.getValid());
    }


    public void delete(String id) {
        taskRepository.deleteById(Long.valueOf(id));
    }
}
