package com.taskmanager.tasks.Controller;

import com.taskmanager.tasks.Dto.CreateTaskDTO;
import com.taskmanager.tasks.Dto.ErrorResponseDTO;
import com.taskmanager.tasks.Dto.UpdateTaskDTO;
import com.taskmanager.tasks.Entity.TaskEntity;
import com.taskmanager.tasks.Service.TaskService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks=taskService.getTasksAll();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskBiId(Integer id){
        var tasks=taskService.getTasksById(id);
        if(tasks==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("")
    public  ResponseEntity<TaskEntity> addTasks(@RequestBody CreateTaskDTO createTaskDTO) throws ParseException {
        var task=taskService.setTasks(createTaskDTO.getTitle(),createTaskDTO.getDescription(),createTaskDTO.getDeadline());
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<TaskEntity>> deleteById(@PathVariable("id") Integer id){
        var task= taskService.deleteTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<TaskEntity> updateById(@PathVariable("id") Integer id,@RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        var task=taskService.updateTaskById(id, updateTaskDTO.getTitle(),updateTaskDTO.getDescription(),updateTaskDTO.getDeadline(),updateTaskDTO.getCompleted());
        if(task== null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date formate"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error::!!"));
    }

}
