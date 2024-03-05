package com.todomanagement.serviceimpl;

import com.todomanagement.Dto.TodoDto;
import com.todomanagement.entity.Todo;
import com.todomanagement.repository.TodoRepository;
import com.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService{
	@Autowired
	private TodoRepository todorepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// converting tododto to entity
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = todorepository.save(todo);
		// convert jpa entity into dto
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		// TODO Auto-generated method stub
		Todo todo = todorepository.findById(id).get();

		return modelMapper.map(todo, TodoDto.class);
	}

}
