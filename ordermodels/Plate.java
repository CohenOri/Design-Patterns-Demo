package ModernProgParadigms.ordermodels;

import ModernProgParadigms.workermodels.WorkerCapabilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;


public class Plate implements Meal {

    private final static Logger logger = Logger.getLogger(Plate.class.getName());
    private String recipe;
    private String description;
    private Double cost;
    private int estimatedTimeToComplete;
    private WorkerCapabilities requiredWorkerCapability;
    private String title;

    public Plate() {
        // Here in real-world scenario I would read from a meals file, instead of just hard-coding meals data.
        // Meals file could be edited using the managing UI.
        this.recipe =  "Put meal on Plate\n";
        this.description = "Plate based meal, which include few items";
        this.cost = 19.99;
        this.estimatedTimeToComplete = 0;
        this.requiredWorkerCapability = WorkerCapabilities.COOK;
        this.title = "Plate";
    }

    @Override
    public String recipe() {
        return recipe;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Double cost() {
        return cost;
    }

    @Override
    public Integer estimatedTimeToComplete() {
        return estimatedTimeToComplete;
    }

    @Override
    public void handle() {
        logger.log(Level.INFO, "Getting a plate...");
    }

    @Override
    public WorkerCapabilities requiredWorkerCapability() {
        return requiredWorkerCapability;
    }

    /**
     * @return how the item called "Coca Cola", "Chicken" etc.
     */
    @Override
    public String title() {
        return title;
    }
}
