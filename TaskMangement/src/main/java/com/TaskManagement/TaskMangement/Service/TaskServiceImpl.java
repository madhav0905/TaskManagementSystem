package com.TaskManagement.TaskMangement.Service;

import com.TaskManagement.TaskMangement.Entity.Task;
import com.TaskManagement.TaskMangement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

@Autowired
    private TaskRepository taskRepository;


    @Override
    public Task getTaskByTaskId(Long id) {
       Optional<Task>  task=taskRepository.findById(id);
       if(task.isPresent())
       {
           return task.get();
       }
       return null;
    }
    @Override
    public List<Task> getTaskByAssingee(UUID id)
    {
        List<Task> tasksbymanager=taskRepository.findByAssignerId(id);
        if(tasksbymanager.size()>0)
        {
            System.out.println(tasksbymanager);return tasksbymanager;

        }

       return null;
    }
    @Override
    public Task saveTask(Task t)
    {
            return taskRepository.save(t);
    }
    @Override
    public Task updateTask(Task task){
        return taskRepository.save(task);
    }
}
