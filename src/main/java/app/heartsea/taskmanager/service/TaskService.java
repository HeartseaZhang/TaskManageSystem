package app.heartsea.taskmanager.service;

import app.heartsea.taskmanager.domain.Task;
import app.heartsea.taskmanager.dao.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getAllTasks() {
        return taskMapper.findAll();
    }

    public Task getTaskById(Long id) {
        return taskMapper.findById(id);
    }

    public void createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        if (task.getCreatorUserId() == null) {
            throw new IllegalArgumentException("Creator user ID cannot be null");
        }
        taskMapper.insert(task);
    }

    public void updateTask(Task task) {
        task.setCompletedAt(LocalDateTime.now());
        taskMapper.update(task);
    }

    public void deleteTask(Long id) {
        taskMapper.delete(id);
    }
}
