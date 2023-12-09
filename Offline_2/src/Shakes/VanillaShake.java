package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public class VanillaShake extends Shake{
    public VanillaShake(Vector<String> ingredients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        super("Vanilla Shake", 190, ingredients, isLactoseFree, toppings);
    }
}
