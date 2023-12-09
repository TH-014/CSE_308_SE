package builders;

import ingredients.Candy;
import ingredients.Cookies;
import ingredients.CustomizedIngredients;
import Shakes.Shake;

import java.util.Vector;

public abstract class ShakeBuilder {
    private final Vector<CustomizedIngredients> toppings;
    private final Vector<String> ingredients;
    private boolean isLactoseFree;
    private final String name;

    public ShakeBuilder(String name) {
        toppings = new Vector<>();
        ingredients = new Vector<>();
        isLactoseFree = false;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void addCandy() {
        toppings.add(new Candy());
    }

    public Vector<CustomizedIngredients> getToppings() {
        return toppings;
    }

    public Vector<String> getIngredients() {
        return ingredients;
    }

    public void addCookie() {
        toppings.add(new Cookies());
    }

    public boolean isLactoseFree() {
        return isLactoseFree;
    }

    public void setLactoseFree(boolean lactoseFree) {
        isLactoseFree = lactoseFree;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public abstract Shake getShake();
}
