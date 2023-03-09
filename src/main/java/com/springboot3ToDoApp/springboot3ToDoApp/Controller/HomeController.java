package com.springboot3ToDoApp.springboot3ToDoApp.Controller;

import com.springboot3ToDoApp.springboot3ToDoApp.Services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    @Autowired
    ToDoService toDoService;


    @GetMapping("/")
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",toDoService.getAll());
        return modelAndView;
    }

}
