package com.todomanagement.service;

import com.todomanagement.Dto.TodoDto;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
}
