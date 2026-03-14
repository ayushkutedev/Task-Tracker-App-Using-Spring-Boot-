package com.ayush.TaskTrackingApp.mappers;

import com.ayush.TaskTrackingApp.domain.dto.TaskListDto;
import com.ayush.TaskTrackingApp.domain.entities.Task;
import com.ayush.TaskTrackingApp.domain.entities.TaskList;

public interface TaskListMapper {
     TaskList fromDto(TaskListDto taskListDto);

     TaskListDto toDto(TaskList taskList);


}
