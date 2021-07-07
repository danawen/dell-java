import java.time.LocalDateTime;

public class TimesheetEntry {

    private String task;
    private String projectName;
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private static int NEXTID = 1;


    public TimesheetEntry(String projectName, String task){
        this.projectName = projectName;
        this.task = task;
        this.startTime = LocalDateTime.now();
        this.id = NEXTID;
        NEXTID ++;
    }

    public void updateEndTime(){
        if(endTime != null){
            throw new IllegalStateException("End time already set");
        }

        endTime = LocalDateTime.now();
    }

    public String getTask() {
        return task;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
