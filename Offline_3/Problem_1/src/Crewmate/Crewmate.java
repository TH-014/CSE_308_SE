package Crewmate;

public class Crewmate implements CrewmateInterface{

    private final String name;
    public Crewmate(String name)
    {
        this.name = name;
    }
    @Override
    public boolean login(String name) {
        if(name.equals(this.name))
        {
            System.out.println("Welcome Crewmate!");
            return true;
        }
        else
        {
            System.out.println("You are not a crewmate!");
            return false;
        }
    }

    @Override
    public void logout() {

    }

    @Override
    public void repair() {

    }

    @Override
    public void work() {

    }
}
