package com.taskmanager.tasks.Controller;

import com.taskmanager.tasks.Dto.CreateTaskDTO;
import com.taskmanager.tasks.Dto.ErrorResponseDTO;
import com.taskmanager.tasks.Dto.TaskResponseDTO;
import com.taskmanager.tasks.Dto.UpdateTaskDTO;
import com.taskmanager.tasks.Entity.TaskEntity;
import com.taskmanager.tasks.Service.NotesService;
import com.taskmanager.tasks.Service.TaskService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final NotesService notesService;
    private ModelMapper modelMapper = new ModelMapper();


    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks=taskService.getTasksAll();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var tasks=taskService.getTasksById(id);
        var notes =notesService.getNotesFromTaskId(id);
        if(tasks==null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse =modelMapper.map(tasks, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
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
