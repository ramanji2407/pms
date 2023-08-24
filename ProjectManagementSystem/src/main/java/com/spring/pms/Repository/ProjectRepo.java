package com.spring.pms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pms.Entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
      public Project findById(int id);
}
