package com.todomanagement.controller;

import com.todomanagement.Dto.TodoDto;
import com.todomanagement.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/api/todo")
@RestController
@AllArgsConstructor
public class TodoController {
	@Autowired
    private TodoService todoservice;
   
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto tododto) {
        TodoDto savedTodo = todoservice.addTodo(tododto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto tododto = todoservice.getTodo(id);
        return new ResponseEntity<>(tododto, HttpStatus.OK);
    }

}
