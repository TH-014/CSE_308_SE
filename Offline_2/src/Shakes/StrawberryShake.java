package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public class StrawberryShake extends Shake{
    public StrawberryShake(Vector<String> ingredients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        super("Strawberry Shake", 200, ingredients, isLactoseFree, toppings);
    }

}
