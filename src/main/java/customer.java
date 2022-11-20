
/**
 * Natthawee Koengfak 6213125
 * Nicharee Chalermsuksri 6213198
 */
import java.util.*;

public class customer implements Comparable<customer> {

    String name;
    String type;
    ArrayList<Integer> orders;
    int totalBill;

    int totalWeight;

    customer(String n, String t, ArrayList<Integer> o) {
        name = n;
        type = t;
        orders = o;
        totalBill = totalWeight = 0;
    }

    public void cal(ArrayList<product> pd, ArrayList<postage> pt) {

        System.out.printf("%-7s>> ", name);
        for (int i = 0; i < orders.size(); i++) {
            ii temp = pd.get(i).update(orders.get(i));
            totalBill += temp.p;
            totalWeight += temp.w;
            System.out.printf("%s (%2d)\t", pd.get(i).getName(), orders.get(i));
            
        }
        int postageCost = 0;
        for (postage i : pt) {
            if (i.check(type, totalWeight)) {
                postageCost = i.getRate();
                break;
            }
        }
        System.out.printf("\n          product price = %5d  total weight = %5d", totalBill, totalWeight);
        totalBill += postageCost;
        System.out.printf("\n          postage(E)    = %5d  total bill   = %5d\n", postageCost, totalBill);
      
    }

    public int compareTo(customer other) {
        return (this.totalBill == other.totalBill)
                ? this.name.compareTo(other.name) : other.totalBill - this.totalBill;

    }

    public void print() {
        System.out.printf("%-8s bill =%6d\n", name, totalBill);
    }

}
