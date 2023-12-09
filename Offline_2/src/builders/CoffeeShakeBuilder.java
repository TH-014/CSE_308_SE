package builders;

import Shakes.CoffeeShake;
import Shakes.Shake;

public class CoffeeShakeBuilder extends ShakeBuilder{
    public CoffeeShakeBuilder() {
        super("Coffee Shake");
    }
    @Override
    public Shake getShake() {
        return new CoffeeShake(getIngredients(), isLactoseFree(), getToppings());
    }
}
