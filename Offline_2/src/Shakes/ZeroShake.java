package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public class ZeroShake extends Shake{
    public ZeroShake(Vector<String> ingredients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        super("Zero Shake", 240, ingredients, isLactoseFree, toppings);
    }
}
