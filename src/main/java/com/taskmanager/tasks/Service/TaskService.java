package com.taskmanager.tasks.Service;

import com.taskmanager.tasks.Dto.TaskResponseDTO;
import com.taskmanager.tasks.Entity.TaskEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private List<TaskEntity> tasks= new ArrayList<>();
    Integer taksId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity setTasks(String title, String description , String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taksId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(String.valueOf(deadlineFormatter.parse(deadline)));
        task.setCompleted(false);
        tasks.add(task);
        taksId++;
        return  task;
    }

    public List<TaskEntity> getTasksAll(){
      List<TaskEntity> abc= tasks.stream().toList();
      return abc;

    }

    public TaskEntity getTasksById(int id){
//        return tasks.stream().findAny().filter(task->task.getId()==id);
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<TaskEntity> deleteTaskById(int id){
        List<TaskEntity> abc = tasks.stream().filter(task->task.getId()!=id).toList();
        tasks=abc;
        return abc;
    }

    public TaskEntity updateTaskById(int taksId,String title, String description , String deadline,Boolean completed) throws ParseException {
        TaskEntity task = getTasksById(taksId);
        if(task==null){
            throw new IllegalArgumentException("Task with ID " + taksId + " not found");
        }
        if(title!=null){
        task.setTitle(title);
        }
        if(description!=null){
            task.setDescription(description);
        }
        if(deadline!= null){
            task.setDeadline(deadline);
        }
        if(completed!= null){
            task.setCompleted(completed);
        }


//        task.setTitle(title);
//        task.setDescription(description);
//        task.setDeadline(String.valueOf(deadlineFormatter.parse(deadline)));
//        task.setCompleted(false);
//        tasks.add(task);
        return task;
    }
}
