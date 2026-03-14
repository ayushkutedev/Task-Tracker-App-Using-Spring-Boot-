package com.ayush.TaskTrackingApp.services.impl;

import com.ayush.TaskTrackingApp.domain.entities.Task;
import com.ayush.TaskTrackingApp.domain.entities.TaskList;
import com.ayush.TaskTrackingApp.domain.entities.TaskPriority;
import com.ayush.TaskTrackingApp.domain.entities.TaskStatus;
import com.ayush.TaskTrackingApp.mappers.TaskListMapper;
import com.ayush.TaskTrackingApp.repositories.TaskListRepository;
import com.ayush.TaskTrackingApp.repositories.TaskRepository;
import com.ayush.TaskTrackingApp.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskList_Id(taskListId);
    }


    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()) {
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));


        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now


        );

         return taskRepository.save(taskToSave);

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);

    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task Must have an id !");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task Ids do not match! !");
        }
        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task must have a valid priority!");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have a valid status!");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new  IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);

    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);

    }
}
