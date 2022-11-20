
/**
 * Natthawee Koengfak 6213125
 * Nicharee Chalermsuksri 6213198
 */
import java.util.*;
import java.io.*;

class fileInput {

    private boolean opened;
    private String inFile;
    private final Scanner keyType;
    public ArrayList<product> products;
    public ArrayList<postage> postages;
    public ArrayList<customer> customers;

    fileInput() {
        products = new ArrayList<>();
        postages = new ArrayList<>();
        customers = new ArrayList<>();
        keyType = new Scanner(System.in);
    }

    public void inputAll() {
        input("products");
        input("postages");
        input("customers");
    }

    void input(String index) {
        opened = false;
        while (!opened) {
            System.out.println("Enter file name for " + index);
            inFile = keyType.next();
            try (Scanner scan = new Scanner(new File(inFile));) {
                opened = true;
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    String buf[] = line.split(",");
                    if ("products".equals(index)) {

                        products.add(new product(buf[0],
                                Integer.parseInt(buf[1].trim()),
                                Integer.parseInt(buf[2].trim())));
                    }
                    if ("postages".equals(index)) {

                        postages.add(new postage(buf[0].trim(),
                                Integer.parseInt(buf[1].trim()),
                                buf[2].trim().equals("inf") ? Integer.MAX_VALUE : Integer.parseInt(buf[2].trim()),
                                Integer.parseInt(buf[3].trim())));
                    }
                    if ("customers".equals(index)) {
                        try {
                            if (buf.length != 2 + products.size()) {
                                throw new Exception("Number of input error");
                            }
                            if (!"E".equals(buf[1].trim()) && !"R".equals(buf[1].trim())) {
                                throw new Exception("Postage type error");
                            }
                            ArrayList<Integer> orders = new ArrayList<>();
                            for (int i = 2; i < 2 + products.size(); i++) {
                                int num = Integer.parseInt(buf[i].trim());
                                if (num < 0) {
                                    throw new Exception("Nagative number error");
                                }
                                orders.add(num);
                            }
                            customers.add(new customer(buf[0], buf[1].trim(), orders));
                        } catch (Exception e) {
                            System.err.println(e);
                            System.err.println("Skip line -> " + line);
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                System.err.println(e);
                opened = false;
            }catch(Exception e)
            {
                System.err.println(e);
                System.exit(-1);
            }
        }
    }

    public void process() {
        customers.forEach(i -> {
           i.cal(products,postages);
        });
    }

    public void printSummary() {
        System.out.println("\n----- Sort customers by total bill -----");
        Collections.sort(customers);
        customers.forEach(i -> {
            i.print();
        });
        System.out.println("\n----- Sort products by total sales in cash -----");
        Collections.sort(products);
        products.forEach(i -> {
            i.print();
        });
    }

}

public class onlineShop {

    public static void main(String[] args) {

        fileInput myFile = new fileInput();
        myFile.inputAll();
        myFile.process();
        myFile.printSummary();
    }
}
//products.txt postages.txt customers.txt