import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManagementSystem fileManagementSystem = new FileManagementSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("root@root:"+fileManagementSystem.getCurrentDirectory().getDirectory() + "\\$ ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            fileManagementSystem.executeCommand(command);
        }
    }
}