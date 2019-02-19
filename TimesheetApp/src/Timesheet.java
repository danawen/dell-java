import java.util.ArrayList;
import java.util.List;

public class Timesheet {

    private List<TimesheetEntry> database;

    public Timesheet(){
        this.database = new ArrayList<>();
    }
    public TimesheetEntry get(int id){
        for(TimesheetEntry entry: database){
            if(id == entry.getId()){
                return entry;
            }
        }
        return null;
    }

    public TimesheetEntry add(String project, String description){
    	TimesheetEntry newEntry = new TimesheetEntry(project, description);
        database.add(newEntry);
        return newEntry;
    }

    public void delete(TimesheetEntry entry){
        database.remove(entry);
    }

    public TimesheetEntry stop(TimesheetEntry entry){
        entry.updateEndTime();
        return entry;
    }

    public List<TimesheetEntry> list(String project, boolean activeOnly){
        List<TimesheetEntry> results  = new ArrayList<>();
        for(TimesheetEntry entry:database){
            if(project != null && !project.equals(entry.getProjectName())){
                continue;
            }

            if(activeOnly &&  entry.getEndTime() != null){
                continue;
            }

            results.add(entry);

        }
        return results;
    }


}
