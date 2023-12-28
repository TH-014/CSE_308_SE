package Imposter;

public class Imposter {

    public void login() {
        System.out.println("We won’t tell anyone; you are an imposter.");
    }

    public void logout() {
        System.out.println("See you again Comrade Imposter.");
    }

    public void work() { //Even though the name of the method is work(). but actually it is kill()
        System.out.println("Trying to kill a crewmate.\n" +
                "Successfully killed a crewmate.");
    }
    public void repair() { //Even though the name of the method is repair(). but actually it is damage()
        System.out.println("Damaging the spaceship.");
    }
}
