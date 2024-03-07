package com.todomanagement.controller;

import com.todomanagement.Dto.TodoDto;
import com.todomanagement.service.TodoService;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
       List<TodoDto> todos =  todoservice.getAllTodos();
       return  new ResponseEntity<>(todos ,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public  ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id){
       TodoDto updatedtodo = todoservice.updateTodo(todoDto,id);
        return  new ResponseEntity<>(updatedtodo,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteTodo(@PathVariable Long id){
       todoservice.deleteTodo(id);
       return  new ResponseEntity<>("Todo deleted succesfully",HttpStatus.OK);
    }
    @PatchMapping("{id}/complete")
    public  ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
     TodoDto updateTodo =   todoservice.completeTodo(id);
     return  new ResponseEntity<>(updateTodo,HttpStatus.OK);
    }
    @PatchMapping("{id}/incomplete")
    public  ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto updateTodo =   todoservice.incompleteTodo(id);
        return  new ResponseEntity<>(updateTodo,HttpStatus.OK);
    }
}
