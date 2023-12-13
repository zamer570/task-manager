package com.example.taskmanager.service;

import com.example.taskmanager.exception.ResourceNotFound;
import com.example.taskmanager.model.Note;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public String createTask(Task task){
        taskRepository.save(task);
        return "task created";
    }

    public List<Task> getTasks(Boolean completed){
        if(isNull(completed)){
            return (List<Task>) taskRepository.findAll();
        }
        return taskRepository.findByCompleted(completed);
    }

    public Task getTask(int id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()){
            throw new ResourceNotFound("no task found for given id");
        }
        return task.get();
    }

    public String editTask(int id,Task task){

        task.setId(id);
        Optional<Task> existingTask = taskRepository.findById(id);
        if(existingTask.isPresent()){
            Task newTask = existingTask.get();
            populateNewTask(task, newTask);
            taskRepository.save(newTask);
            return "Task modified successfully";
        }
        throw new ResourceNotFound("Task id mentioned is not present");
    }

    private static void populateNewTask(Task task, Task newTask) {
        if(!isNull(task.getTitle())){
            newTask.setTitle(task.getTitle());
        }
        if(!isNull(task.getDescription())){
            newTask.setDescription(task.getDescription());
        }
        if(!isNull(task.getDeadline())){
            newTask.setTitle(task.getDeadline());
        }
        if(!isNull(task.getNotes())){
            newTask.setNotes(task.getNotes());
        }
        if(!isNull(task.getCompleted())){
            newTask.setCompleted(task.getCompleted());
        }
    }

    public String deleteTask(int id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return "Task deleted successfully";
        }
        throw new ResourceNotFound( "Task id mentioned is not present");
    }

    public List<Note> getNotesForTask(int taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return taskRepository.findNotesByTaskId(taskId);
        }
        throw new ResourceNotFound( "Task id mentioned is not present");
    }

    public String addNoteForTask(int taskId,Note note){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            Task newTask = task.get();
             newTask.getNotes().add(note);
            taskRepository.save(newTask);
            return "Added notes to task successfully";
        }
        throw new ResourceNotFound( "Task id mentioned is not present");
    }

    public String deleteNote(int taskId,int noteId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            List<Note> notes = task.get().getNotes();
            boolean isNotePresent = false;
            for(int i=0;i<notes.size();i++){
                if(notes.get(i).getId() == noteId){
                    isNotePresent = true;
                    notes.remove(notes.get(i));
                    i--;
                }
            }
            if(!isNotePresent){
                throw new ResourceNotFound("Note id mentioned is not present");
            }
            taskRepository.save(task.get());
            return "removed note for the task";
        }
        throw new ResourceNotFound( "Task id mentioned is not present");
    }

}
