import java.util.List;

public class Controller {

    Timesheet timesheet;
    ConsoleUtils consoleUtils;

    public Controller(){
        this.timesheet = new Timesheet();
        this.consoleUtils = new ConsoleUtils();
    }

    public void start() {

        consoleUtils.printHelp();


        boolean quit = false;
        while(!quit) {

            String input = consoleUtils.promptString("> ");
            String[] actionParts = input.split(" ");

            String action = actionParts[0].trim();


            if (action.equals("add")) {

                processAddAction();

            } else if (action.equals("delete")) {

                processDeleteAction(actionParts);

            } else if (action.equals("stop")) {

                processStopAction(actionParts);

            } else if (action.equals("list")) {

                processListAction(actionParts);

            } else if (action.equals("quit")) {

                quit = true;

            } else if (action.equals("help")) {

            	consoleUtils.printHelp();

            } else if(action.length() ==0 ){
                // do nothing.
            } else {

                consoleUtils.error("Invalid action");
            }
        }

    }


    public void processStopAction(String[] actionParts){

        if(actionParts.length > 2){
            consoleUtils.error("Too many inputs to stop command");
            return;
        }

        if(actionParts.length <= 1){
            consoleUtils.error("Stop command requires a valid integer id");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(actionParts[1]);
        } catch (Exception e){
            consoleUtils.error("Stop command requires a valid integer id");
            return;
        }

        TimesheetEntry entry = timesheet.get(id);
        if(entry == null){
            consoleUtils.error("Could not find entry with id "+id);
            return;
        }

        try{
        	timesheet.stop(entry);
            consoleUtils.info("Entry stopped");
        } catch (Exception e){
            consoleUtils.error("Stop command failed, was entry already stopped?");
        }

    }

    public void processDeleteAction(String[] actionParts){

        if(actionParts.length > 2){
            consoleUtils.error("Too many inputs to delete command");
            return;
        }

        if(actionParts.length <= 1){
            consoleUtils.error("Delete command requires a valid integer id");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(actionParts[1]);
        } catch (Exception e){
            consoleUtils.error("Delete command requires a valid integer id");
            return;
        }

        TimesheetEntry entry = timesheet.get(id);
        if(entry == null){
            consoleUtils.error("Could not find entry with id "+id);
            return;
        }

        timesheet.delete(entry);
        consoleUtils.info("Entry deleted");



    }

    public void processListAction(String[] actionParts){
        if(actionParts.length > 3){
            consoleUtils.error("Too many inputs to list command");
            return;
        }
        boolean activeOnly = false;
        String project = null;
        for(String part: actionParts){
            if(part.equals("-a")){
                activeOnly = true;
            } else if(part.equals("list")){
                continue;
            } else {
                project = part;
            }
        }

        List<TimesheetEntry> entries = timesheet.list(project, activeOnly);
        consoleUtils.printList(entries);

    }




    public void processAddAction(){
        String project = consoleUtils.promptString("Project:");
        String description = consoleUtils.promptString("Task:");

        if(project == null || project.length() == 0){
            consoleUtils.error("A project is required");
        } else {
        	timesheet.add(project, description);
            consoleUtils.info("Entry added");
        }

    }
}
