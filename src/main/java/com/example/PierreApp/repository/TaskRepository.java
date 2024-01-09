package com.example.PierreApp.repository;
// TaskRepository.java (Repository)

import com.example.PierreApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query method to find tasks by status
    List<Task> findByStatus(String status);
}