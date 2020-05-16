package com.ajb.todo.task;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<TaskDto> listAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(
            task -> modelMapper.map(task, TaskDto.class)
        ).collect(Collectors.toList());
    }

    public TaskDto create(TaskDto task) {
        Task newTask = new Task(task.getName());
        newTask = taskRepository.save(newTask);
        return modelMapper.map(newTask, TaskDto.class);
    }

    public Boolean toggleCompletion(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.getOne(taskId);
            task.setComplete(!task.isComplete());
            task = taskRepository.save(task);
            return task.isComplete();
        } else {
            return null;
        }
    }

    public void delete(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        }
    }
}
