package com.todomanagement.serviceimpl;

import com.todomanagement.Dto.TodoDto;
import com.todomanagement.Exception.ResourceNotFoundException;
import com.todomanagement.entity.Todo;
import com.todomanagement.repository.TodoRepository;
import com.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


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
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));


		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
	List<Todo> todos =	todorepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
	Todo	todo = todorepository.findById(id).
				orElseThrow(() ->  new ResourceNotFoundException("Todo not found with id "+id));
     todo.setTitle(todoDto.getTitle());
	 todo.setDescription(todoDto.getDescription());
	 todo.setCompleted(todoDto.isCompleted());
	Todo updatedtodo = todorepository.save(todo);
		return modelMapper.map(updatedtodo,TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
	Todo todo =	todorepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Todo not found with id"+id));
		todorepository.deleteById(id);

	}

	@Override
	public TodoDto completeTodo(Long id) {
	Todo todo =	todorepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " +id));
		todo.setCompleted(Boolean.TRUE);
		Todo updatedtodo = todorepository.save(todo);
		return modelMapper.map(updatedtodo,TodoDto.class) ;
	}

	@Override
	public TodoDto incompleteTodo(Long id) {
		Todo todo =	todorepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " +id));
			todo.setCompleted(Boolean.FALSE);
		Todo updatedtodo = todorepository.save(todo);
		return modelMapper.map(updatedtodo,TodoDto.class) ;
	}


}
