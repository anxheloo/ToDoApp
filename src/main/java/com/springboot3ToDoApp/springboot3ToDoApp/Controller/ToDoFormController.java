package com.springboot3ToDoApp.springboot3ToDoApp.Controller;

import com.springboot3ToDoApp.springboot3ToDoApp.Services.ToDoService;
import com.springboot3ToDoApp.springboot3ToDoApp.models.ToDoItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ToDoFormController {

    @Autowired
    ToDoService toDoService;

    //WAY 1
//    @GetMapping("/create-todo")
//    public String showCreateForm(Model model)
//    {
//        ToDoItem todoItem = new ToDoItem();
//        model.addAttribute("todoItem",todoItem);
//        return "new-todo-item";
//    }


    //WAY 2
    @GetMapping("/create-todo")
    public String showCreateForm(Model model,ToDoItem todoItem)
    {
        model.addAttribute("todoItem",todoItem);
        return "new-todo-item";
    }


    //WAY 1
    @PostMapping("/todo")
    public String createToDOItem(@Valid ToDoItem toDoItem, BindingResult result,Model model)
    {
        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setComplete(toDoItem.isComplete());

        toDoService.save(item);
        System.out.println(item.toString());
        return "redirect:/";
    }



        //WAY 2 - USING @MODEL ATTRIBUTE
//    @PostMapping("/todo")
//    public String createToDOItem(@ModelAttribute @Valid ToDoItem toDoItem, BindingResult result)
//    {
//        toDoService.save(toDoItem);
//        return "redirect:/";
//    }


    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id,Model model)
    {
        ToDoItem toDoItem = toDoService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found!"));

        //WE CAN ALSO USE THIS WAY
//        Optional<ToDoItem> todoItem = toDoService.getById(id);
//        if(todoItem.isPresent())
//        {
//            todoItem.get();
//        }

        toDoService.delete(toDoItem);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model)
    {
        ToDoItem toDoItem = toDoService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found!"));

        model.addAttribute("todo",toDoItem);
        return "edit-todo-item";
    }


    //BindingResult result,

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id,@Valid ToDoItem toDoItem,Model model)
    {

        ToDoItem item = toDoService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found!"));

        item.setComplete(toDoItem.isComplete());
        item.setDescription(toDoItem.getDescription());

        toDoService.save(item);
        System.out.println(item.toString());

        return "redirect:/";
    }


}
