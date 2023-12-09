package builders;

import Shakes.ChocolateShake;
import Shakes.Shake;

import java.util.Vector;

public class ChocolateShakeBuilder extends ShakeBuilder{
    public ChocolateShakeBuilder() {
        super("Chocolate Shake");
    }
    @Override
    public Shake getShake() {
        return new ChocolateShake(getIngredients(), isLactoseFree(), getToppings());
    }
}
