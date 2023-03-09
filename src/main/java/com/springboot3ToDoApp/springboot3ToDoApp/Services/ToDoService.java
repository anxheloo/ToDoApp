package com.springboot3ToDoApp.springboot3ToDoApp.Services;

import com.springboot3ToDoApp.springboot3ToDoApp.Repositories.ToDoRepository;
import com.springboot3ToDoApp.springboot3ToDoApp.models.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public Iterable<ToDoItem>getAll()
    {
        return toDoRepository.findAll();
    }



    public Optional<ToDoItem> getById(Long id)
    {
        return toDoRepository.findById(id);
    }



    public ToDoItem save(ToDoItem toDoItem)
    {
        if(toDoItem.getId()==null)
        {
            toDoItem.setCreatedAt(Instant.now());
        }

        toDoItem.setUpdatedAt(Instant.now());
        return toDoRepository.save(toDoItem);
    }


    public void delete(ToDoItem toDoItem)
    {
        toDoRepository.delete(toDoItem);
    }

}
