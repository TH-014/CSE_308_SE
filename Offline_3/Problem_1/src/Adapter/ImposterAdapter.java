package Adapter;

import Crewmate.CrewmateInterface;
import Imposter.Imposter;

public class ImposterAdapter implements CrewmateInterface {
    private final Imposter imposter;

    public ImposterAdapter(Imposter imposter)
    {
        this.imposter = imposter;
    }
    @Override
    public void login() {
        System.out.println("Welcome Crewmate!");
        imposter.login();
    }

    @Override
    public void logout() {
        System.out.println("Bye Bye crewmate.");
        imposter.logout();
    }

    @Override
    public void repair() {
        System.out.println("Repairing the spaceship.");
        imposter.repair();
    }

    @Override
    public void work() {
        System.out.println("Doing research.");
        imposter.work();
    }
}
