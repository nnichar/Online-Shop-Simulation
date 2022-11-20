
/**
 * Natthawee Koengfak 6213125
 * Nicharee Chalermsuksri 6213198
 */
public class postage {

    private final String type;
    private final int minWeight;
    private final int maxWeight;
    private final int rate;

    postage(String t, int mn, int mx, int r) {
        type = t;
        minWeight = mn;
        maxWeight = mx;
        rate = r;
    }

    public boolean check(String t, int weight) {
        return weight > minWeight && weight <= maxWeight && t.equals(type);
    }

    public int getRate() {
        return rate;
    }
    
}
