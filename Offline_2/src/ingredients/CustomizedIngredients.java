package ingredients;

public abstract class CustomizedIngredients {
    public static final int lactoseFreeMilk = 60;
    private final String name;
    private final int price;

    public CustomizedIngredients(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
}
