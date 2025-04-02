package Task_Tracker.CLI.Controllers;



import Task_Tracker.CLI.Entity.TaskEntity;
import Task_Tracker.CLI.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
     List<TaskEntity> tasks= taskService.getAllTasks();
     return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody TaskEntity task){
        taskService.addTAsk(task.getTitle(),task.getDescription());
        return ResponseEntity.ok("Task Added Sucessfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable String id,@RequestBody TaskEntity task){

        boolean update =taskService.updateTask(id,task.getTitle(),task.getDescription(),task.getStatus());
        return update ?ResponseEntity.ok("Task Added Sucessfully"):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id){
        boolean delete = taskService.deleteTask(id);
        return delete ?ResponseEntity.ok("Task Deleted Sucessfully"):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
    }

    @GetMapping("/{status}")
    public  ResponseEntity<List<TaskEntity>> statusSearch(@PathVariable String status){
        List<TaskEntity> tasks = taskService.statusSearch(status);
        return ResponseEntity.ok(tasks);
    }
}
