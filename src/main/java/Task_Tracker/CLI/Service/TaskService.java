package Task_Tracker.CLI.Service;


import Task_Tracker.CLI.Entity.TaskEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
  private static final String file_Path="tasks.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private void ensureFileExists() {
        File file = new File(file_Path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Files.write(Paths.get(file_Path), "[]".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeTasks(List<TaskEntity> tasks) {
        ensureFileExists();

        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tasks);
            Files.write(Paths.get(file_Path), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<TaskEntity> getAllTasks() {
        ensureFileExists();
        try{
            String json=Files.readString(Paths.get(file_Path));
            return objectMapper.readValue(json, new TypeReference<List<TaskEntity>>() {});
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addTAsk(String title, String description) {
        ensureFileExists();
        List<TaskEntity> tasks= getAllTasks();
        int newId = tasks.stream()
                .mapToInt(task -> Integer.parseInt(task.getId()))
                .max()
                .orElse(0) + 1;
       TaskEntity newTask= new TaskEntity(String.valueOf(newId),title,description,"Pending");
       tasks.add(newTask);
       writeTasks(tasks);

    }

    public boolean updateTask(String id, String title, String description, String status) {
        ensureFileExists();
        List<TaskEntity> taskEntities=getAllTasks();
        for(TaskEntity tasks:taskEntities){
            if(tasks.getId().equals(id)){
                if (title != null) tasks.setTitle(title);
                if (description != null) tasks.setDescription(description);
                if (status != null) tasks.setStatus(status);
                writeTasks(taskEntities);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(String id) {
        ensureFileExists();
        List<TaskEntity> tasks=getAllTasks();
        boolean removed=tasks.removeIf(task -> task.getId().equals(id));
        if(removed){
            writeTasks(tasks);
        }
        return removed;
    }

    public List<TaskEntity> statusSearch(String status) {
        ensureFileExists();
        List<TaskEntity> tasks = getAllTasks();
        return tasks.stream().filter(task -> task.getStatus().equalsIgnoreCase(status)).toList();
    }
    }

