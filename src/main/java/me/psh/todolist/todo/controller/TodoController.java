package me.psh.todolist.todo.controller;

import me.psh.todolist.todo.dto.TodoCreateDto;
import me.psh.todolist.todo.dto.TodoUpdateDto;
import me.psh.todolist.todo.entity.Todo;
import me.psh.todolist.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(@Valid TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public Todo createTodo(TodoCreateDto todoCreateDto){
        return todoService.createTodoBy(todoCreateDto);
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    Todo getTodo(@PathVariable Long id){
        return todoService.getTodoBy(id);

    }

    @GetMapping("/todos/{isAscendingByCreatedAt}")
    public List<Todo> getTodo(@PathVariable Boolean isAscendingByCreatedAt){
        return todoService.getTodosBy(isAscendingByCreatedAt);

    }

    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid TodoUpdateDto todoUpdateDto){
        return todoService.updateTodoBy(id, todoUpdateDto);
    }

    @DeleteMapping("todos/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodoBy(id);

    }
}
