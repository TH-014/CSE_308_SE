public class Crewmate implements BaseInterface {

    @Override
    public void login() {
        System.out.println("\tWelcome Crewmate!");
    }

    @Override
    public void logout() {
        System.out.println("\tBye Bye crewmate.");
    }

    @Override
    public void repair() {
        System.out.println("\tRepairing the spaceship.");
    }

    @Override
    public void work() {
        System.out.println("\tDoing research.");
    }
}
