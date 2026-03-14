package com.ayush.TaskTrackingApp.domain.dto;

import com.ayush.TaskTrackingApp.domain.entities.TaskPriority;
import com.ayush.TaskTrackingApp.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

//DTO Stands for Data Transfer Object
//DTO helps us to represent these entities in our Rest APIS
//DTO helps us to seperate our domain module form our API contract and give us control over what
//data we expose to clients
public record TaskDto(

        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status

) {


}
