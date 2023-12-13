package com.example.taskmanager.repository;

import com.example.taskmanager.model.Note;
import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {
    List<Task> findByCompleted(boolean completed);

    @Query("select notes from Task where id = ?1")
    List<Note> findNotesByTaskId(int id);

}
