package com.lii.elasticbeanapp.repository;

import com.lii.elasticbeanapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
