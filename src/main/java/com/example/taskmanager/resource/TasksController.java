package com.example.taskmanager.resource;

import com.example.taskmanager.model.Note;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {

    @Autowired
    TaskService taskService;

    @PostMapping("/tasks")
    private ResponseEntity<String> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/tasks")
    private ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) Boolean completed){
        return ResponseEntity.ok(taskService.getTasks(completed));
    }

    @GetMapping("/tasks/{task_id}")
    private ResponseEntity<Task> getTask(@PathVariable(name = "task_id") int id){
        return ResponseEntity.ok(taskService.getTask(id));
    }


    @PatchMapping("/tasks/{task_id}")
    private ResponseEntity<String> editTasks(@PathVariable(name = "task_id") int id,@RequestBody Task task){
        return ResponseEntity.ok(taskService.editTask(id,task));
    }

    @DeleteMapping("/tasks/{task_id}")
    private ResponseEntity<String> deleteTask(@PathVariable(name = "task_id") int id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/tasks/{task_id}/notes")
    private ResponseEntity<List<Note>> getNotesForTask(@PathVariable(name = "task_id") int id){
        return ResponseEntity.ok(taskService.getNotesForTask(id));
    }

    @PostMapping("/tasks/{task_id}/notes")
    private ResponseEntity<String> addNoteForTask(@PathVariable(name = "task_id") int id,@RequestBody Note note){
        return ResponseEntity.ok(taskService.addNoteForTask(id,note));
    }

    @DeleteMapping("/tasks/{task_id}/notes/{notes_id}")
    private ResponseEntity<String> deleteNote(@PathVariable(name = "task_id") int taskId,@PathVariable(name = "notes_id") int noteId){
        return ResponseEntity.ok(taskService.deleteNote(taskId,noteId));
    }

}
