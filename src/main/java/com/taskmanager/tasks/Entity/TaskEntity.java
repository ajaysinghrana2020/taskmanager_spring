package com.taskmanager.tasks.Entity;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    Integer id ;
    String title;
    String description;
    String deadline;
    boolean completed;

}
