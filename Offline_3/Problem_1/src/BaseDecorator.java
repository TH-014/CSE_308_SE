public class BaseDecorator implements BaseInterface{
    private final BaseInterface baseInterface;

    public BaseDecorator(BaseInterface baseInterface) {
        this.baseInterface = baseInterface;
    }

    @Override
    public void login() {
        baseInterface.login();
    }

    @Override
    public void logout() {
        baseInterface.logout();
    }

    @Override
    public void repair() {
        baseInterface.repair();
    }

    @Override
    public void work() {
        baseInterface.work();
    }
}
