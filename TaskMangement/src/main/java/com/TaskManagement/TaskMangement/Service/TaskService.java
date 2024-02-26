package com.TaskManagement.TaskMangement.Service;

import com.TaskManagement.TaskMangement.Entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    public Task getTaskByTaskId(Long id);
    public List<Task> getTaskByAssingee(UUID id);
    public Task saveTask(Task t);
    public Task updateTask(Task task);
}
