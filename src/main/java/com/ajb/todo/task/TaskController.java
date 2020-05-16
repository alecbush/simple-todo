package com.ajb.todo.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<List<TaskDto>> list() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @PatchMapping("/complete/{taskId}")
    public ResponseEntity<Boolean> toggleCompletion(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.toggleCompletion(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity delete(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }
}
