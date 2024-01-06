import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import FileSystem.*;

public class FileManagementSystem {
    private final BaseComponent root;
    private BaseComponent currentDirectory;
    private static final int defaultSize = 0;

    public BaseComponent getCurrentDirectory() {
        return currentDirectory;
    }

    public FileManagementSystem()
    {
        root = new Container("root", FileType.ROOT, getCurrentTime(), null);
        currentDirectory = root;
    }

    private String getCurrentTime()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy hh:mm:ss a");
        return now.format(formatter);
    }

    public void executeCommand(String command) {
        String[] commandParts = command.split(" ");
        switch (commandParts[0]) {
            case "cd":
                if (commandParts.length == 2) {
                    String name = commandParts[1];
                    if(name.equals(".."))
                    {
                        if(currentDirectory.getParent() != null)
                        {
                            currentDirectory = currentDirectory.getParent();
                        }
                        else
                        {
                            System.out.println("You are already in the root directory!");
                        }
                        break;
                    } else if (name.equals("~")) {
                        currentDirectory = root;
                        break;
                    }
                    boolean isDrive = false;
                    if (name.endsWith(":\\")) {
                        name = name.substring(0, name.length() - 2);
                        isDrive = true;
                    }
                    BaseComponent component = currentDirectory.getComponent(name);
                    if (component != null && ((component.getType() == FileType.DIRECTORY && !isDrive) || component.getType() == FileType.DRIVE)) {
                        currentDirectory = component;
                    } else {
                        System.out.println("No such drive or directory exists!");
                    }
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "ls":
                if (commandParts.length == 2) {
                    String name = commandParts[1];
                    currentDirectory.showDetails(name);
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "list":
                if (commandParts.length == 1) {
                    currentDirectory.showList();
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "delete":
                if (commandParts.length == 2) {
                    String name = commandParts[1];
                    BaseComponent component = currentDirectory.getComponent(name);
                    if (component != null) {
                        if(component.getType() == FileType.FILE)
                        {
                            currentDirectory.removeComponent(component);
                        }
                        else if(component.getType() == FileType.DIRECTORY)
                        {
                            if(component.getComponentCount() == 0)
                                currentDirectory.removeComponent(component);
                            else
                                System.out.println("Directory is not empty!");
                        }
                        else
                        {
                            if(component.getComponentCount() == 0)
                                currentDirectory.removeComponent(component);
                            else
                                System.out.println("Drive is not empty!");
                        }
                    } else {
                        System.out.println("No such file or directory exists!");
                    }
                }
                else if(commandParts.length == 3)
                {
                    String name = commandParts[2];
                    String optional = commandParts[1];
                    if(!optional.equals("-r"))
                    {
                        System.out.println("Invalid command flag!");
                        break;
                    }
                    BaseComponent component = currentDirectory.getComponent(name);
                    if (component != null) {
                        if(component.getType() == FileType.FILE)
                            System.out.println("Warning: You are about to delete a file using '-r' !");
                        currentDirectory.removeComponent(component);
                    } else {
                        System.out.println("No such file or directory exists!");
                    }
                }
                else
                {
                    System.out.println("Invalid command!");
                }
                break;
            case "mkdir":
                if (commandParts.length == 2) {
                    if(currentDirectory.getType() == FileType.ROOT)
                    {
                        System.out.println("You cannot create a directory in root!");
                        break;
                    }
                    String name = commandParts[1];
                    BaseComponent newDirectory = new Container(name, FileType.DIRECTORY, getCurrentTime(), currentDirectory);
                    currentDirectory.addComponent(newDirectory);
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "touch":
                if (commandParts.length == 3 || commandParts.length == 2) {
                    String name = commandParts[1];
                    int size;
                    if(commandParts.length == 3)
                    {
                        try{
                            size = Integer.parseInt(commandParts[2]);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid size!");
                            break;
                        }
                    }
                    else
                        size = defaultSize;
                    BaseComponent newFile = new File(name, FileType.FILE, size, getCurrentTime(), currentDirectory);
                    currentDirectory.addComponent(newFile);
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "mkdrive":
                if (commandParts.length == 2) {
                    if(currentDirectory.getType() != FileType.ROOT)
                    {
                        System.out.println("You cannot create a drive in a directory!");
                        break;
                    }
                    String name = commandParts[1];
                    BaseComponent newDirectory = new Container(name, FileType.DRIVE, getCurrentTime(), currentDirectory);
                    currentDirectory.addComponent(newDirectory);
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
}
