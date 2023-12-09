package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public class CoffeeShake extends Shake{
    public CoffeeShake(Vector<String> ingredients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        super("Coffee Shake", 250, ingredients, isLactoseFree, toppings);
    }
}
