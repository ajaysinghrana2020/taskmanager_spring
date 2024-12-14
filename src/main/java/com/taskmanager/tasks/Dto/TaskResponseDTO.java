package com.taskmanager.tasks.Dto;

import com.taskmanager.tasks.Entity.NotesEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
        Integer id ;
        String title;
        String description;
        String deadline;
        boolean completed;
        private List<NotesEntity> notes;
}
