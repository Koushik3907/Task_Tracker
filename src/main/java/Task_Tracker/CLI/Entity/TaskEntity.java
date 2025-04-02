package Task_Tracker.CLI.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {


    private String id;

    private String title;
    private String description;
    private String Status;


}
