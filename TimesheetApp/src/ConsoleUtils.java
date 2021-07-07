import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtils {

	/* Member variables */
	
    private Scanner scanner;
    private DateTimeFormatter timeFormatter;
    
    /* Constructor */
    
    public ConsoleUtils(){
        scanner = new Scanner(System.in);
        timeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
    }

	/* Methods */
	
	/*
	 * Prints the menu of actions to the console
	 */
    public void printHelp(){
        System.out.println("Valid commands: ");
        System.out.println("  list [PROJECT] [-a]   list entries; project and/or active flag are optional");
        System.out.println("  add                   add an entry, and set the start time");
        System.out.println("  stop ID               update the entry's end time");
        System.out.println("  delete ID             delete entry with the ID");
        System.out.println("  help                  print help");
        System.out.println("  quit                  quit the app");
        System.out.println();

    }

	/*
	 * Prints an informational message to the console
	 */
    public void info(String msg){
        System.out.println("["+msg+"]");
        System.out.println();
    }

	/*
	 * Prints an error message to the console
	 */
    public void error(String msg){
        System.out.println("[ERROR: "+msg+"]");
        System.out.println();
        }

	/*
	 * Prompts the user to enter input
	 * Returns the text entered by the user
	 */
    public String promptString(String label){
        System.out.print(label+" ");
        return scanner.nextLine();
    }

	/*
	 * Prints a list of TimesheetEntry objects in a pretty table
	 */
    public void printList(List<TimesheetEntry> entries){
        int longestProject =  7;
        int longestTask = 4;

        for(TimesheetEntry entry : entries){
            if(entry.getProjectName().length() > longestProject){
                longestProject = entry.getProjectName().length();
            }
            if(entry.getTask().length() > longestTask) {
            	longestTask = entry.getTask().length();
            }
        }

        String projectHeader = String.format("%"+longestProject+"s", "Project");
        String projectUnderline = "";
        for(int i=0;i<longestProject;i++){
            projectUnderline+="-";
        }
        
        String taskHeader = String.format("%"+longestTask+"s", "Task");
        String taskUnderline = "";
        for(int i=0;i<longestTask;i++){
            taskUnderline+="-";
        }

        System.out.println(" ID | "+projectHeader   +" | "+taskHeader   +" | Start          | End            ");
        System.out.println("----+-"+projectUnderline+"-+-"+taskUnderline+"-+----------------+----------------");

        for(TimesheetEntry entry : entries){
            String project = String.format("%-"+longestProject+"s", entry.getProjectName());
            String task = String.format("%-"+longestTask+"s", entry.getTask());
            String startTime = entry.getStartTime().format(timeFormatter);
            String endTime = "";
            if(entry.getEndTime() != null){
                endTime = entry.getEndTime().format(timeFormatter);
            }
            String line = String.format(" %2s | %s | %s | %s | %s ", entry.getId(), project, task, startTime, endTime);
            System.out.println(line);
        }

        if(entries.size() == 1){
            System.out.println("[1 entry]");
        } else {
            System.out.println("["+entries.size()+" entries]");
        }

        System.out.println();
    }
}