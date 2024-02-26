package com.TaskManagement.TaskMangement.Controller;

import com.TaskManagement.TaskMangement.Entity.Task;
import com.TaskManagement.TaskMangement.Entity.TaskStatus;
import com.TaskManagement.TaskMangement.Entity.User;
import com.TaskManagement.TaskMangement.Service.EmployeeService;
import com.TaskManagement.TaskMangement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/createTask")
    public String getCreateForm(Principal p,Model theModel)
    {

       List<User> employees= employeeService.findAll();
        Task t=new Task();

       theModel.addAttribute("task",t);
        theModel.addAttribute("employees",employees);

        return "createTask";
    }
    @PostMapping("/postForm")
    public String createTask(Principal p,@ModelAttribute Task task)
    {
       System.out.println(task);
        User u=employeeService.getCurrentUser(p);
        if(u==null)
        {
            throw new RuntimeException("NO USER LOGGED IN");

        }
        task.setAssigner(u);
       Task newTask= taskService.saveTask(task);
        System.out.println(newTask);
        return "redirect:/api/v1/mytasks";
    }
    @GetMapping("/assignedtasks")
    public String assignedTask(Principal p,Model themModel) throws  RuntimeException
    {
        User u=employeeService.getCurrentUser(p);
        if(u==null)
        {
            throw new RuntimeException("NO USER LOGGED IN");

        }

      List<Task> res= taskService.getTaskByAssingee(u.getId());
        themModel.addAttribute("tasks",res);
        return "assignedtasks";
    }
    @GetMapping("/updateStatus/{taskId}")
    public String viewTaskDetails(@PathVariable("taskId") Long taskId, Model theModel) {

        Task task = taskService.getTaskByTaskId(taskId);
        System.out.println(task);
        theModel.addAttribute("task", task);
        List<User> employees= employeeService.findAll();
        theModel.addAttribute("employees",employees);

        return "createTask";
    }

}
