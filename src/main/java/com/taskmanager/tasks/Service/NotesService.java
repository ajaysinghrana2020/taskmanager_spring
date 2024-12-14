package com.taskmanager.tasks.Service;

import com.taskmanager.tasks.Entity.NotesEntity;
import com.taskmanager.tasks.Entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class NotesService {

    private final TaskService taskService;

    private int noteId=1;
    private List<NotesEntity> tasks= new ArrayList<>();

    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }
    HashMap<Integer,List<NotesEntity>> map =new HashMap<>();

    public HashMap<Integer,List<NotesEntity>> setNotesFromTaskId(int taskId, String title, String body) throws IllegalAccessException {
        System.out.println(taskId+"   "+ title+"   "+" "+body);
        TaskEntity task = taskService.getTasksById(taskId);

        NotesEntity notesToSave = new NotesEntity();

        if(task==null){
            throw new IllegalAccessException();
        }
        if(map.containsKey(taskId)){

        }
        notesToSave.setId(noteId);
        notesToSave.setBody(body);
        notesToSave.setTitle(title);
        tasks.add(notesToSave);
        map.put(taskId,tasks);
//        taskService.no(notesToSave.getId(),notesToSave.getBody(),notesToSave.getTitle());
        noteId++;
        return map;
    }

    public List<NotesEntity> getNotesFromTaskId(int taskId) {
        return map.get(taskId);
    }
}
