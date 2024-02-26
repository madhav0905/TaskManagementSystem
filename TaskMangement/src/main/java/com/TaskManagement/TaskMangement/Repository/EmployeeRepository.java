package com.TaskManagement.TaskMangement.Repository;

import com.TaskManagement.TaskMangement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<User, UUID> {
   public Optional<User> findUserByUsername(Long username);

   @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.tasks where u.username=:username")
   public Optional<User> findAllUsersWithTasks(@Param("username") Long username) ;

}
