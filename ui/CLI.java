package ModernProgParadigms.ui;

import ModernProgParadigms.ui.exceptions.NoSuchOperation;
import ModernProgParadigms.ordermodels.*;
import ModernProgParadigms.workermodels.WorkerCapabilities;

import java.util.Scanner;

import static java.lang.System.exit;

public class CLI extends AbstractUI {

    private Integer incrementalOrderID;

    /* note that in real-world scenario it's a better idea that the UI would send the controller a JSON
     * and let him parse and build an Order object, a different approach, possibly better is to use the GUI class
     * as a proxy to the real GUI (Web based etc.) which probably won't be written in java.
     * Therefore this class is quick and dirty CLI implementation for demonstration purposes only
     * (not thoroughly designed like the rest of code) - not a real production ready solution
     */
    public CLI() {
        super();
        this.incrementalOrderID = 1;
    }

    @Override
    public void render() {
        while (true) {
            System.out.println(
                    "Welcome to our Restaurant\n" +
                            "1 - placing new order\n" +
                            "2 - creating new worker\n" +
                            "3 - starting the work (a.k.a service)\n" +
                            "q - quit\n");
            String operation = new Scanner(System.in).nextLine();
            try {
                switch (operation) {
                    case "q":
                        System.out.println("Goodbye!");
                        exit(0);
                        break;
                    case "1":
                        newOrder();
                        break;
                    case "2":
                        newWorker();
                        break;
                    case "3":
                        startWorking();
                        break;
                    default:
                        throw new NoSuchOperation("Operation num: " + operation + " is not valid");
                }
            } catch (NoSuchOperation e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void newOrder() {
        Order order = new RestaurantOrder(incrementalOrderID);
        while (true) {
            System.out.println(
                    "--- New Order ---\n" +
                    "1 - add item to order\n" +
                    "p - place the order\n" +
                    "b - back to main menu\n");
            String operation = new Scanner(System.in).nextLine();
            try {
                switch (operation) {
                    case "1":
                        Meal meal = new Plate(); // currently we only support plate based meals
                        System.out.println("Plate is mandatory,\nDo you want Chicken? (yes/any other answer is no)\n");
                        String chicken = new Scanner(System.in).nextLine();
                        if(chicken.equals("yes")) {
                            meal = new Chicken(meal);
                        }
                        System.out.println("Do you want Fried Rice? (yes/any other answer is no)\n");
                        String friedRice = new Scanner(System.in).nextLine();
                        if(friedRice.equals("yes")) {
                            meal = new FriedRice(meal);
                        }
                        order.addItem(meal);
                        break;
                    case "p":
                        System.out.println("placing order: " + incrementalOrderID.toString());
                        for (UIObserver o : uiObservers) {
                            o.newOrder(order);
                        }
                        this.incrementalOrderID++;
                        return;
                    case "b":
                        return;
                    default:
                        throw new NoSuchOperation("Operation num: " + operation + " is not valid");
                }
            } catch (NoSuchOperation e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void newWorker() {
        Integer id;
        String workerType;
        System.out.println("--- Create worker ---\n"
                + "Insert ID, then String of worker type out of:\n"
                + java.util.Arrays.asList(WorkerCapabilities.values()));
        try {
            id = new Scanner(System.in).nextInt();
            workerType = new Scanner(System.in).nextLine();
        } catch (Exception e) {
            System.out.println("Something wrong with your input");
            return;
        }
        for (UIObserver o : uiObservers) {
            o.newWorker(workerType, id);
        }
    }

    @Override
    public void startWorking() {
        for (UIObserver o : uiObservers) {
            o.startWorking();
        }
    }
}
