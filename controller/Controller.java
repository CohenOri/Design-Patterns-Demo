package ModernProgParadigms.controller;

import ModernProgParadigms.controller.exceptions.NoSuchWorkerType;
import ModernProgParadigms.controller.exceptions.NotSupportedRestaurantType;
import ModernProgParadigms.ui.UI;
import ModernProgParadigms.ui.UIObserver;
import ModernProgParadigms.ordermodels.Order;
import ModernProgParadigms.ordermodels.OrderItem;
import ModernProgParadigms.workermodels.Worker;
import ModernProgParadigms.workermodels.factory.AbstractWorkersFactory;
import ModernProgParadigms.workermodels.factory.FancyRestaurantWorkersFactory;
import ModernProgParadigms.workerslogic.iteration.Iterator;
import ModernProgParadigms.workerslogic.WorkersPool;
import ModernProgParadigms.workerslogic.assignstrategy.AssignStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements UIObserver {

    private final static Logger logger = Logger.getLogger(Controller.class.getName());
    private WorkersPool workersPool;
    private RestaurantType restaurantType;
    private AbstractWorkersFactory workersFactory;
    private UI ui;
    private List<Order> orders;

    public Controller(AssignStrategy assignStrategy, File workersSource, UI ui, RestaurantType rt) {
        try {
            this.workersPool = new WorkersPool(assignStrategy, workersSource);
            this.restaurantType = rt;
            // note that switch-case as below usage is considered bad practice,
            // should be replaced with command pattern, as implemented below in newWorker method
            // left here just to demonstrate the future support in more restaurant types.
            switch (restaurantType) {
                case FancyRestaurant:
                    this.workersFactory = new FancyRestaurantWorkersFactory();
                    break;
                case Falafel:
                    logger.log(Level.SEVERE, "Falafel is nice but not yet supported");
                    throw new NotSupportedRestaurantType("Not supported type of restaurant: " + restaurantType.name());
                default:
                    throw new NotSupportedRestaurantType("Not supported type of restaurant");
            }
            this.ui = ui;
            this.orders = new ArrayList<>();
        } catch (NotSupportedRestaurantType e) {
            e.printStackTrace();
        }
    }

    /**
     * Add the order to orders list then assigns a matching worker to handle each orderItem
     * @param order new order to handle
     */
    public void newOrder(Order order) {
        /* note that in real-world scenario it's a better idea to parse here a JSON sent by the UI
         * and build here an Order object, a different approach, possibly better is to use the GUI class
         * as a proxy to the real GUI (Web based etc.) which probably won't be written in java */

        this.orders.add(order);
        for (OrderItem item : order.getItems()) {
            this.workersPool.assign(item);
        }
    }

    public void newWorker(String workerType, Integer id) {
        try {
            // note that in real-world scenario it's probably a better idea to read workerType options from conf file
            Worker worker;
            CreateWorkerCommand createCommand = new CreateWorkerCommand(this.workersFactory);
            worker = createCommand.createWorker(workerType, id);
            this.workersPool.addWorker(worker);
        } catch (NoSuchWorkerType e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Tell the workers to start to work - a.k.a start the service in the catering business
     */
    public void startWorking() {
        Iterator<Worker> workersIterator = workersPool.iterator();
        while (workersIterator.hasNext()) {
            workersIterator.next().work();
        }
    }

    /**
     * Method to runProgram the program
     */
    public void runProgram() {
        // Start the program
        this.ui.addUIObserver(this);
        this.ui.render();
    }
}
