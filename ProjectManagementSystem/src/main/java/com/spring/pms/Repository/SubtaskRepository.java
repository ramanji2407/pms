package com.spring.pms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pms.Entity.Project;
import com.spring.pms.Entity.Subtask;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    public Subtask findById(long id);

}
