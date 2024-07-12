package app.heartsea.taskmanager.service.impl;

import app.heartsea.taskmanager.domain.Task;
import app.heartsea.taskmanager.dao.mapper.TaskMapper;
import app.heartsea.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> getAllTasks() {
        return taskMapper.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskMapper.findById(id);
    }

    @Override
    public void createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        if (task.getCreatorUserId() == null) {
            throw new IllegalArgumentException("Creator user ID cannot be null");
        }
        taskMapper.insert(task);
    }

    @Override
    public void updateTask(Task task) {
        task.setCompletedAt(LocalDateTime.now());
        taskMapper.update(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskMapper.delete(id);
    }
}
