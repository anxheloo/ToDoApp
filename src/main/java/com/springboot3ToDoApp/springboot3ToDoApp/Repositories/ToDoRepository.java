package com.springboot3ToDoApp.springboot3ToDoApp.Repositories;

import com.springboot3ToDoApp.springboot3ToDoApp.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem,Long> {

}
