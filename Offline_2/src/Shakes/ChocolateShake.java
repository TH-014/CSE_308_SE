package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public class ChocolateShake extends Shake{
    public ChocolateShake(Vector<String> ingredients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        super("Chocolate Shake", 230, ingredients, isLactoseFree, toppings);
    }
}
