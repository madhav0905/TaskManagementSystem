package com.TaskManagement.TaskMangement.Repository;

import com.TaskManagement.TaskMangement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository  extends JpaRepository<Task,Long> {

    public List<Task> findByAssignerId(UUID id);
}
