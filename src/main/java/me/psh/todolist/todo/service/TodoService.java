package me.psh.todolist.todo.service;

import me.psh.todolist.todo.dto.TodoCreateDto;
import me.psh.todolist.todo.dto.TodoUpdateDto;
import me.psh.todolist.todo.entity.Todo;
import me.psh.todolist.todo.repository.TodoRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodoBy(TodoCreateDto todoCreateDto) {
        Todo todo = new Todo(
                todoCreateDto.getTitle(),
                todoCreateDto.getDescription(),
                todoCreateDto.getDueDate()
        );

        return todoRepository.save(todo);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoBy(@PathVariable Long id) {
        return todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



    public Todo updateTodoBy(@PathVariable Long id, @Valid TodoUpdateDto todoUpdateDto) { //throws 표현 필요 없음 RunTimeException 상속받음
        Todo todo = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        todo.setTitle(todoUpdateDto.getTitle());
        todo.setDescription(todoUpdateDto.getDescription());
        todo.setDueDate(todoUpdateDto.getDueDate());
        return todoRepository.save(todo);

    }

    public void deleteTodoBy(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        todoRepository.delete(todo);
    }


    public List<Todo> getTodosBy(Boolean isAscendingByCreatedAt) {
        List<Todo> todos = todoRepository.findAll();

        if(!isAscendingByCreatedAt){
            todos.sort(new Comparator<Todo>() {
                @Override
                public int compare(Todo todo01, Todo todo02) {
                    return todo02.getCreatedAt().compareTo(todo01.getCreatedAt());
                }
            })
        }
        return todos;
    }

}
