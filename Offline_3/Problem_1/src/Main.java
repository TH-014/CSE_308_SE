import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SpaceShip spaceship = new SpaceShip();
        boolean flag = true;
        while (flag) {
            System.out.print("Enter command: ");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("exit"))
                flag = false;
            else
                spaceship.execute(line);
        }
    }

}