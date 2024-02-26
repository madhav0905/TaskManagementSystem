package com.TaskManagement.TaskMangement.Service;

import com.TaskManagement.TaskMangement.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
  public User getCurrentUser(Principal p);
    public User findUserDetailsById(UUID Id);
   public  List<User> findAll();
    public User findUserDetailsByUserName(Long username);
}
