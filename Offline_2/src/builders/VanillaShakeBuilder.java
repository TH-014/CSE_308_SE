package builders;

import Shakes.Shake;
import Shakes.VanillaShake;

public class VanillaShakeBuilder extends ShakeBuilder{
    public VanillaShakeBuilder() {
        super("Vanilla Shake");
    }
    @Override
    public Shake getShake() {
        return new VanillaShake(getIngredients(), isLactoseFree(), getToppings());
    }
}
