package com.au.example.service;



import com.au.example.data.model.Todo;
import com.au.example.dto.TodoDTO;
import com.au.example.dto.TodoHistoryDTO;
import com.au.example.data.repository.TodoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<TodoDTO> getTodos() {
    return todoRepository.findAll().stream()
        .map(todo -> new TodoDTO(todo.getId(), todo.getTitle(), todo.isDone())).toList();
  }

  public TodoDTO getTodo(Long id) {
    return todoRepository.findById(id)
        .map(todo -> new TodoDTO(todo.getId(), todo.getTitle(), todo.isDone())).orElseThrow();
  }

  public TodoDTO save(TodoDTO todoDTO) {
    Todo newTodo = Todo.builder()
        .title(todoDTO.title())
        .done(todoDTO.done())
        .build();
    Todo todo = todoRepository.save(newTodo);
    return new TodoDTO(todo.getId(), todo.getTitle(), todo.isDone());

  }

  public void update(Long id, TodoDTO todoDTO) {
    Todo todo = todoRepository.findById(id).orElseThrow();
    todo.setDone(true);
    todo.setTitle(todoDTO.title());
    todoRepository.save(todo);
  }

  public List<TodoHistoryDTO> getTodoHistoryDTO(){
    return todoRepository.findAll().stream()
        .map(todo -> new TodoHistoryDTO(todo.getId(), todo.getTitle(), todo.isDone(), todo.getCreatedOn(), todo.getLastUpdatedOn())).toList();
  }
}