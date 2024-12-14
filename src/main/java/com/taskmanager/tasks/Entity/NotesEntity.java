package com.taskmanager.tasks.Entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotesEntity {
    private int id;
    private String title;
    private String body;
}
