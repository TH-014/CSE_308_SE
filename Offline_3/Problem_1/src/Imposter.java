public class Imposter extends BaseDecorator {

    public Imposter(BaseInterface disguisedCrewmate)
    {
        super(disguisedCrewmate);
    }
    @Override
    public void login() {
        super.login(); //work as crewmate
        //additional work as imposter
        System.out.println("\tWe won’t tell anyone; you are an imposter.");
    }

    @Override
    public void logout() {
        super.logout(); //work as crewmate
        //additional work as imposter
        System.out.println("\tSee you again Comrade Imposter.");
    }

    @Override
    public void repair() {
        super.repair(); //work as crewmate
        //additional work as imposter
        System.out.println("\tDamaging the spaceship.");
    }

    @Override
    public void work() {
        super.work(); //work as crewmate
        //additional work as imposter
        System.out.println("\tTrying to kill a crewmate.\n" +
                "\tSuccessfully killed a crewmate.");
    }
}
