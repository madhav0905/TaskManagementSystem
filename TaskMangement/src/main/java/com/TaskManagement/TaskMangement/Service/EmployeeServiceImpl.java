package com.TaskManagement.TaskMangement.Service;

import com.TaskManagement.TaskMangement.Entity.User;
import com.TaskManagement.TaskMangement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
   public User getCurrentUser(Principal p)
    {

        System.out.println(p.getName());
        String username=p.getName();
        Long convertedUsername=Long.parseLong(username);
       Optional<User> res=employeeRepository.findUserByUsername(convertedUsername);
       if(res.isPresent())
       {
           return res.get();
       }
       return null;
    }

    @Override
    public User findUserDetailsById(UUID id) {
        Optional<User> userDetails=employeeRepository.findById(id);
        if(userDetails.isPresent())
        {

        }
        return null;
    }

    @Override
    public List<User> findAll() {
    return employeeRepository.findAll();
    }

    @Override
    public User findUserDetailsByUserName(Long username) {
        Optional<User> res=employeeRepository.findUserByUsername(username);
        if(res.isPresent())
        {
            return res.get();
        }
        return null;
    }


}
