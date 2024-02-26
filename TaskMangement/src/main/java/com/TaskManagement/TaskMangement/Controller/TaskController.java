package com.TaskManagement.TaskMangement.Controller;

import com.TaskManagement.TaskMangement.Entity.Task;
import com.TaskManagement.TaskMangement.Entity.TaskStatus;
import com.TaskManagement.TaskMangement.Entity.User;
import com.TaskManagement.TaskMangement.Service.EmployeeService;
import com.TaskManagement.TaskMangement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("currentUser")
    public String getCurrentUser(Principal p)
    {
        if(employeeService.getCurrentUser(p)!=null)
        {
            System.out.println(employeeService.getCurrentUser(p).getUsername());
        }
        System.out.println("hi");
        return "index";
    }
    @GetMapping("/employees")
    public String getEmployees(Model theModel)
    {
        List<User> res= (List<User>) employeeService.findAll();
        System.out.println(res);
        for (User user : res) {
            // Access user properties and perform actions
            //System.out.println(user.getUsername());
            System.out.println(user.getId());
            System.out.println(user.getEmail());
            List<Task>  tasks=user.getTasks();
            for(Task t:tasks)
            {
                System.out.println(t);
            }
            // etc.
        }
        //theModel.addAttribute("users",res);
        return "index";
    }

    @GetMapping("/mytasks")
    public String getUserTasks(Principal principal, Model theModel) throws RuntimeException
    {

        Long usernameInLong=Long.parseLong(principal.getName());
        User res= employeeService.findUserDetailsByUserName(usernameInLong);
        ArrayList<Task> ActiveTasks = new ArrayList<>() ;
        if(res==null)
        {
            throw  new RuntimeException("NO USER LOGGED IN");
        }

         List<Task>  tasks=res.getTasks();

         for(Task t:tasks)
         {if(!t.getStatus().equals(TaskStatus.COMPLETED)) {
             ActiveTasks.add(t);
         }


        }
        theModel.addAttribute("tasks",ActiveTasks);
        return "showActiveTasks";

    }
    @GetMapping("/mycompletedtasks")
    public String getCurrentUserCompletedTasks(Principal principal,Model theModel)
    {
        Long usernameInLong=Long.parseLong(principal.getName());
        User res= employeeService.findUserDetailsByUserName(usernameInLong);
        ArrayList<Task> CompletedTasks = new ArrayList<>() ;
        System.out.println(res);
        if(res==null)
        {
            throw  new RuntimeException("NO USER LOGGED IN");
        }

            List<Task>  tasks=res.getTasks();

            for(Task t:tasks)
            {if(t.getStatus().equals(TaskStatus.COMPLETED)) {
                CompletedTasks.add(t);
            }


        }
        theModel.addAttribute("tasks",CompletedTasks);
        return "completed";
    }
  //  @GetMapping("/updateStatus")
  @GetMapping("/updateStatus/{taskId}")
  public String viewTaskDetails(@PathVariable("taskId") Long taskId, Model model) {

      Task task = taskService.getTaskByTaskId(taskId);
      System.out.println(task);
      model.addAttribute("task", task);

      return "update-task";
  }
  @PostMapping("/updateStatusForm")
  public String updateTaskStatus(Principal p,@ModelAttribute Task task)
  {
      //System.out.println(task);
     taskService.saveTask(task);
      return "redirect:/api/v1/mytasks";
      /*
      return "index";
      if(u==null)
      {
          throw new RuntimeException("NO USER LOGGED IN");

      }
      task.setAssigner(u);
      Task newTask= taskService.saveTask(task);
      System.out.println(newTask);
      return "index";
      */

  }

}
