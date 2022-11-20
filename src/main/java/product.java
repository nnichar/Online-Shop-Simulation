
/**
 * Natthawee Koengfak 6213125
 * Nicharee Chalermsuksri 6213198
 */
public class product implements Comparable<product> {

    private final String name;
    private final int price;
    private final int weight;
    private int totalSalesInCash;
    private int totalSalesInUnit;

    product(String n, int p, int w) {
        name = n;
        price = p;
        weight = w;
        totalSalesInCash = totalSalesInUnit = 0;
    }

    public ii update(int n) {
        totalSalesInCash += n * price;
        totalSalesInUnit += n;
        return new ii(n * price, n * weight);
    }

    public int compareTo(product other) {
        if (this.totalSalesInCash == other.totalSalesInCash) {
            if (this.totalSalesInUnit == other.totalSalesInUnit) {
                return this.name.compareTo(other.name);
            }
            return other.totalSalesInUnit - this.totalSalesInUnit;
        }
        return other.totalSalesInCash - this.totalSalesInCash;
    }

    public void print() {
        System.out.printf("%-18s total sales = %6d B, %4d units\n", name, totalSalesInCash, totalSalesInUnit);
    }

    public String getName() {
        return name;
    }

}
