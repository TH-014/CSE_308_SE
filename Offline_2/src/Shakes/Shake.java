package Shakes;

import ingredients.CustomizedIngredients;

import java.util.Vector;

public abstract class Shake {
    private final String name;
    private final int price;
    private final boolean isLactoseFree;
    private final Vector<String> ingedients;
    private final Vector<CustomizedIngredients> toppings;

    public Shake(String name, int price, Vector<String> ingedients, boolean isLactoseFree, Vector<CustomizedIngredients> toppings) {
        this.name = name;
        this.price = price;
        this.isLactoseFree = isLactoseFree;
        this.ingedients = ingedients;
        this.toppings = toppings;
    }

    public String getName() { return name; }
    public int getPrice() {
        int totalPrice = this.price;
        if(isLactoseFree) {
            totalPrice += CustomizedIngredients.lactoseFreeMilk;
        }
        for (CustomizedIngredients topping : toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }

    public boolean isLactoseFree() {
        return isLactoseFree;
    }

    public String getIngredients() {
        String ingredients = "";
        if(isLactoseFree) {
            ingredients += "Lactose Free ";
        }
        for (String ingredient : ingedients) {
            ingredients += ingredient + ", ";
        }
        ingredients += "\n";
        if(!toppings.isEmpty() || isLactoseFree) {
            ingredients += "Customization:\n";
            if(isLactoseFree) {
                ingredients += "Almond Milk ............. " + CustomizedIngredients.lactoseFreeMilk + "\n";
            }
            for (CustomizedIngredients topping : toppings) {
                ingredients += topping.getName() + " ............... " + topping.getPrice() + "\n";
            }
        }
        return ingredients;
    }

    public String toString()
    {
        return "Name: " + getName() + "\n" + "Base Price: ............ " + price + "\n" + "Ingredients: " + getIngredients() + "Total Price: ............ " + getPrice() + "\n";
    }
}
