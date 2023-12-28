package Crewmate;

public class Crewmate implements CrewmateInterface{

    @Override
    public void login() {
        System.out.println("Welcome Crewmate!");
    }

    @Override
    public void logout() {
        System.out.println("Bye Bye crewmate.");
    }

    @Override
    public void repair() {
        System.out.println("Repairing the spaceship.");
    }

    @Override
    public void work() {
        System.out.println("Doing research.");
    }
}
