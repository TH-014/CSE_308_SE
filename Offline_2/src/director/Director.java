package director;

import builders.ShakeBuilder;

public class Director {
    private final ShakeBuilder shakeBuilder;
    private boolean isCandyAdded;
    private boolean isCookieAdded;
    public Director(ShakeBuilder shakeBuilder) {
        this.shakeBuilder = shakeBuilder;
    }
    private void makeChocolateShake() {

        shakeBuilder.addIngredient("Milk");
        shakeBuilder.addIngredient("Sugar");
        shakeBuilder.addIngredient("Chocolate syrup");
        shakeBuilder.addIngredient("Chocolate ice cream");
    }
    public void makeCoffeeShake() {
        shakeBuilder.addIngredient("Milk");
        shakeBuilder.addIngredient("Sugar");
        shakeBuilder.addIngredient("Coffee");
        shakeBuilder.addIngredient("Jello");
    }
    public void makeStrawberryShake() {
        shakeBuilder.addIngredient("Milk");
        shakeBuilder.addIngredient("Sugar");
        shakeBuilder.addIngredient("Strawberry syrup");
        shakeBuilder.addIngredient("Strawberry ice cream");
    }
    public void makeVanillaShake() {
        shakeBuilder.addIngredient("Milk");
        shakeBuilder.addIngredient("Sugar");
        shakeBuilder.addIngredient("Vanilla flavoring");
        shakeBuilder.addIngredient("Jello");
    }
    public void makeZeroShake() {
        shakeBuilder.addIngredient("Milk");
        shakeBuilder.addIngredient("Sweetener");
        shakeBuilder.addIngredient("Vanilla flavoring");
        shakeBuilder.addIngredient("Sugar-free Jello");
    }
    public void makeItLactoseFree()
    {
        shakeBuilder.setLactoseFree(true);
    }
    public void addCandy()
    {
        if(!isCandyAdded)
        {
            shakeBuilder.addCandy();
            isCandyAdded = true;
        }
    }
    public void addCookie()
    {
        if(!isCookieAdded)
        {
            shakeBuilder.addCookie();
            isCookieAdded = true;
        }
    }
    public void makeShake() {
        switch (shakeBuilder.getName()) {
            case "Chocolate Shake" -> makeChocolateShake();
            case "Coffee Shake" -> makeCoffeeShake();
            case "Strawberry Shake" -> makeStrawberryShake();
            case "Vanilla Shake" -> makeVanillaShake();
            case "Zero Shake" -> makeZeroShake();
        }
    }
}
