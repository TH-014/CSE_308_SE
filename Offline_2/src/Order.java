import Shakes.Shake;

import java.util.Vector;

public class Order {
    private final Vector<Shake> shakes;

    public Order() {
        this.shakes = new Vector<>();
    }
    public void addShake(Shake shake) {
        shakes.add(shake);
    }
    public int getNumberOfShakes() {
        return shakes.size();
    }
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Shake shake : shakes) {
            totalPrice += shake.getPrice();
        }
        return totalPrice;
    }
    public String toString() {
        String order = "Total shakes ordered: " + getNumberOfShakes() + "\n";
        int shakeNumber = 1;
        for (Shake shake : shakes) {
            order += "\nShake " + shakeNumber++ + ":\n-------------------------------------\n";
            order += shake.toString();
        }
        order += "==================================================\n";
        order += "Grand Total Price: ...... " + getTotalPrice() + "\n\n\n";
        return order;
    }
}
