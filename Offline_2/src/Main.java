import java.util.Scanner;
import Shakes.*;
import builders.*;
import director.*;

public class Main {
    public static void main(String[] args) {
        boolean isQuit = false;
        while (!isQuit)
        {
            Order order = null;
            boolean isOpen = false;
            System.out.println("Enter 'o' to order, 'e' to exit ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("o"))
            {
                order = new Order();
                isOpen = true;
            }
            else if(input.equals("e"))
            {
                isQuit = true;
            }
            else
            {
                System.out.println("Invalid input");
                continue;
            }
            while (isOpen)
            {
                System.out.println("Choose an option:");
                System.out.println("1. Chocolate Shake");
                System.out.println("2. Coffee Shake");
                System.out.println("3. Strawberry Shake");
                System.out.println("4. Vanilla Shake");
                System.out.println("5. Zero Shake");
                System.out.println("6. Confirm and close order");
                int choice = scanner.nextInt();
                ShakeBuilder shakeBuilder = null;
                Director director;
                switch (choice)
                {
                    case 1:
                        shakeBuilder = new ChocolateShakeBuilder();
                        break;
                    case 2:
                        shakeBuilder = new CoffeeShakeBuilder();
                        break;
                    case 3:
                        shakeBuilder = new StrawberryShakeBuilder();
                        break;
                    case 4:
                        shakeBuilder = new VanillaShakeBuilder();
                        break;
                    case 5:
                        shakeBuilder = new ZeroShakeBuilder();
                        break;
                    case 6:
                        if(order.getNumberOfShakes()>0)
                        {
                            isOpen = false;
                            System.out.println(order);
                        }
                        else
                        {
                            System.out.println("You must order at least one shake");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }
                if(choice!=6)
                {
                    director = new Director(shakeBuilder);
                    System.out.println("Do you want to make it lactose free? (y/n): ");
                    String lactoseChoice = scanner.next();
                    if(lactoseChoice.equals("y"))
                    {
                        director.makeItLactoseFree();
                    }
                    System.out.println("Do you want to add candy? (y/n): ");
                    String candyChoice = scanner.next();
                    if(candyChoice.equals("y"))
                    {
                        director.addCandy();
                    }
                    System.out.println("Do you want to add cookie? (y/n): ");
                    String cookieChoice = scanner.next();
                    if(cookieChoice.equals("y"))
                    {
                        director.addCookie();
                    }
                    director.makeShake();
                    Shake shake = shakeBuilder.getShake();
                    order.addShake(shake);
                }
            }
        }
    }
}