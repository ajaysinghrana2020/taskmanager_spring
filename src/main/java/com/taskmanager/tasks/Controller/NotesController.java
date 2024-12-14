package com.taskmanager.tasks.Controller;

import com.taskmanager.tasks.Dto.CreateNotesDTO;
import com.taskmanager.tasks.Entity.NotesEntity;
import com.taskmanager.tasks.Service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/tasks/{id}/notes")
public class NotesController {

    public final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }
    @PostMapping("")
    public HashMap<Integer, List<NotesEntity>> setNotes(@PathVariable("id") Integer id, @RequestBody CreateNotesDTO createNotesDTO) throws IllegalAccessException {
        System.out.println(id+"-------"+"  "+createNotesDTO.getTitle()+" ----- "+createNotesDTO.getBody());
        return notesService.setNotesFromTaskId(id,createNotesDTO.getTitle(),createNotesDTO.getBody());
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getAllNotes(@PathVariable("id") Integer id){
        List<NotesEntity> notesEntity=notesService.getNotesFromTaskId(id);
        return ResponseEntity.ok(notesEntity);
    }
}
