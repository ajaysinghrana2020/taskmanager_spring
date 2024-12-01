package com.taskmanager.tasks.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
@Setter
@Data
public class TaskEntity {
    Integer id ;
    String title;
    String description;
    String deadline;
    boolean completed;
}
