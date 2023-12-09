package builders;

import Shakes.Shake;
import Shakes.ZeroShake;

public class ZeroShakeBuilder extends ShakeBuilder{
    public ZeroShakeBuilder() {
        super("Zero Shake");
    }
    @Override
    public Shake getShake() {
        return new ZeroShake(getIngredients(), isLactoseFree(), getToppings());
    }
}
