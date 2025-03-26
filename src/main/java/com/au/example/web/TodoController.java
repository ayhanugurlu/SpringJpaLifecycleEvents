package com.au.example.web;

import com.au.example.dto.TodoDTO;
import com.au.example.dto.TodoHistoryDTO;
import com.au.example.service.TodoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/todos")
  public ResponseEntity<List<TodoDTO>> getTodos() {
    return ResponseEntity.ok(todoService.getTodos());
  }

  @PostMapping("/todo")
  public ResponseEntity<TodoDTO> saveTodo(@RequestBody TodoDTO todoDTO) {
    return ResponseEntity.ok(todoService.save(todoDTO));
  }

  @GetMapping("/todo/{id}")
  public ResponseEntity<TodoDTO> getDoneTodo(@PathVariable Long id) {
    return ResponseEntity.ok(todoService.getTodo(id));
  }

  @PutMapping("/todo/{id}")
  public void updateDoneTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
    todoService.update(id, todoDTO);
  }

  @GetMapping("/todo/history")
  public ResponseEntity<List<TodoHistoryDTO>> getTodoHistory() {
    return ResponseEntity.ok(todoService.getTodoHistoryDTO());
  }
}