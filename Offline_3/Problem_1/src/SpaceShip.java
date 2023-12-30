import Adapter.ImposterAdapter;
import Crewmate.Crewmate;
import Imposter.Imposter;

import java.util.HashMap;

public class SpaceShip {
    HashMap<String, Crewmate> crewMap;
    HashMap<String, ImposterAdapter> imposterMap;
    Crewmate curr_crewmate;
    ImposterAdapter curr_imposter;

    SpaceShip() {
        crewMap = new HashMap<>();
        imposterMap = new HashMap<>();
        curr_crewmate = null;
        curr_imposter = null;
        execute("create crewmate crew1");
        execute("create imposter imp1");
        System.out.println();    }

    void execute(String line)
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
                crewMap.put(tokens[2].toLowerCase(), crewmate);
                System.out.println("Crewmate "+tokens[2].toLowerCase()+" created successfully.");
            }
            else if(tokens[1].equalsIgnoreCase("imposter"))
            {
                Imposter imposter = new Imposter();
                ImposterAdapter imposterAdapter = new ImposterAdapter(imposter);
                imposterMap.put(tokens[2].toLowerCase(), imposterAdapter);
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
            if(crewMap.containsKey(tokens[1].toLowerCase()))
            {
                curr_crewmate = crewMap.get(tokens[1].toLowerCase());
                curr_crewmate.login();
            }
            else if(imposterMap.containsKey(tokens[1].toLowerCase()))
            {
                curr_imposter = imposterMap.get(tokens[1].toLowerCase());
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
