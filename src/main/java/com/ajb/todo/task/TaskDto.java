package com.ajb.todo.task;

import lombok.Data;

@Data
public class TaskDto {

    private Long id;
    private String name;
    private boolean complete;
}
