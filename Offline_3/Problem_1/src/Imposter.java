public class Imposter implements BaseInterface {
    private final BaseInterface disguisedCrewmate;

    public Imposter(BaseInterface disguisedCrewmate)
    {
        this.disguisedCrewmate = disguisedCrewmate;
    }
    @Override
    public void login() {
        disguisedCrewmate.login(); //work as crewmate
        //additional work as imposter
        System.out.println("\tWe won’t tell anyone; you are an imposter.");
    }

    @Override
    public void logout() {
        disguisedCrewmate.logout(); //work as crewmate
        //additional work as imposter
        System.out.println("\tSee you again Comrade Imposter.");
    }

    @Override
    public void repair() {
        disguisedCrewmate.repair(); //work as crewmate
        //additional work as imposter
        System.out.println("\tDamaging the spaceship.");
    }

    @Override
    public void work() {
        disguisedCrewmate.work(); //work as crewmate
        //additional work as imposter
        System.out.println("\tTrying to kill a crewmate.\n" +
                "\tSuccessfully killed a crewmate.");
    }
}
