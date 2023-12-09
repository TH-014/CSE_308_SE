package builders;

import Shakes.Shake;
import Shakes.StrawberryShake;

public class StrawberryShakeBuilder extends ShakeBuilder{
    public StrawberryShakeBuilder() {
        super("Strawberry Shake");
    }
    @Override
    public Shake getShake() {
        return new StrawberryShake(getIngredients(), isLactoseFree(), getToppings());
    }
}
