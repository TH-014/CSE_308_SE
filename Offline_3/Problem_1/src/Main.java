import Adapter.ImposterAdapter;
import Crewmate.Crewmate;
import Imposter.Imposter;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, Crewmate> crewmap = new HashMap<>();
    static HashMap<String, ImposterAdapter> impostermap = new HashMap<>();
    static Crewmate curr_crewmate = null;
    static ImposterAdapter curr_imposter = null;
    public static void main(String[] args) {
        execute("create crewmate crew1");
        execute("create imposter imp1");
        System.out.println();
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter command: ");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if(line.equalsIgnoreCase("exit"))
                flag = false;
            else
                execute(line);
        }
    }

    static void execute(String line)
    {
        String[] tokens = line.split(" ");
        if(tokens[0].equalsIgnoreCase("create"))
        {
            if(curr_crewmate != null || curr_imposter != null)
            {
                System.out.println("You cannot create a new user.");
                return;
            }
            if(tokens[1].equalsIgnoreCase("crewmate"))
            {
                Crewmate crewmate = new Crewmate();
                crewmap.put(tokens[2].toLowerCase(), crewmate);
                System.out.println("Crewmate "+tokens[2].toLowerCase()+" created successfully.");
            }
            else if(tokens[1].equalsIgnoreCase("imposter"))
            {
                Imposter imposter = new Imposter();
                ImposterAdapter imposterAdapter = new ImposterAdapter(imposter);
                impostermap.put(tokens[2].toLowerCase(), imposterAdapter);
                System.out.println("Imposter "+tokens[2].toLowerCase()+" created successfully.");
            }
            else
            {
                System.out.println("Invalid user type!");
                return;
            }
        }
        else if(tokens[0].equalsIgnoreCase("login"))
        {
            if(curr_crewmate != null || curr_imposter != null)
            {
                System.out.println("You are already logged in.");
                return;
            }
            if(crewmap.containsKey(tokens[1].toLowerCase()))
            {
                curr_crewmate = crewmap.get(tokens[1].toLowerCase());
                curr_crewmate.login();
            }
            else if(impostermap.containsKey(tokens[1].toLowerCase()))
            {
                curr_imposter = impostermap.get(tokens[1].toLowerCase());
                curr_imposter.login();
            }
            else
            {
                System.out.println("No such user exists!");
                return;
            }
        }
        else if(tokens[0].equalsIgnoreCase("logout"))
        {
            if(curr_crewmate == null && curr_imposter == null)
            {
                System.out.println("You are not logged in.");
                return;
            }
            if(curr_crewmate != null)
            {
                curr_crewmate.logout();
                curr_crewmate = null;
            }
            else {
                curr_imposter.logout();
                curr_imposter = null;
            }
        }
        else if(tokens[0].equalsIgnoreCase("repair"))
        {
            if(curr_crewmate != null)
            {
                curr_crewmate.repair();
            }
            else if(curr_imposter != null)
            {
                curr_imposter.repair();
            }
            else
            {
                System.out.println("You are not logged in.");
                return;
            }
        }
        else if(tokens[0].equalsIgnoreCase("work"))
        {
            if(curr_crewmate != null)
            {
                curr_crewmate.work();
            }
            else if(curr_imposter != null)
            {
                curr_imposter.work();
            }
            else
            {
                System.out.println("You are not logged in.");
                return;
            }
        }
        else
        {
            System.out.println("Invalid command!");
        }
    }
}