package com.ayush.TaskTrackingApp.mappers;

import com.ayush.TaskTrackingApp.domain.dto.TaskDto;
import com.ayush.TaskTrackingApp.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
