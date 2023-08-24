package com.spring.pms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pms.Entity.Subtask;
import com.spring.pms.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findById(long id);

}
