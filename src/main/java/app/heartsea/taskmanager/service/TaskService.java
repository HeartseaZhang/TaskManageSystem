package app.heartsea.taskmanager.service;

import app.heartsea.taskmanager.domain.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);
}
