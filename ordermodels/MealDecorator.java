package ModernProgParadigms.ordermodels;

import ModernProgParadigms.workermodels.WorkerCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public abstract class MealDecorator implements Meal {

    private final static Logger logger = Logger.getLogger(MealDecorator.class.getName());
    // for decoration
    protected Meal meal;
    // define in child c'tor
    protected WorkerCapabilities requiredWorkerCapability;
    protected String recipe;
    protected String description;
    protected Double cost;
    protected int estimatedTimeToComplete;
    protected String title;

    public MealDecorator(Meal meal,
                         WorkerCapabilities requiredWorkerCapability, String recipe,
                         String description, Double cost, int estimatedTimeToComplete, String title) {
        this.meal = meal;
        this.requiredWorkerCapability = requiredWorkerCapability;
        this.recipe = recipe;
        this.description = description;
        this.cost = cost;
        this.estimatedTimeToComplete = estimatedTimeToComplete;
        this.title = title;
    }

    @Override
    public void handle() {
        // for demonstration purposes we'll sleep for the ETC time of the job
        logger.log(
                Level.INFO, "Going to sleep for " + estimatedTimeToComplete().toString() +
                " sec while working on: " + meal.title() + title);

        try {
            long etcInMillisec = TimeUnit.SECONDS.toMillis(estimatedTimeToComplete());
            sleep(etcInMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verify if we need a higher order WorkerCapability to make this item
     * exp: for getting a plate maybe a waiter would suffice,
     * but in order to cook a delicious chicken we need a professional cook, thus return COOK
     * @return updated requiredWorkerCapability for the meal
     */
    @Override
    public WorkerCapabilities requiredWorkerCapability() {
        if(meal.requiredWorkerCapability().compareTo(requiredWorkerCapability) < 0){
            return meal.requiredWorkerCapability();
        }
        return requiredWorkerCapability;
    }

    @Override
    public String recipe() {
        return meal.recipe() + recipe;
    }

    @Override
    public String title() {
        return meal.title() + title;
    }

    @Override
    public String description() {
        return meal.title() + description;
    }

    @Override
    public Double cost() {
        return meal.cost()+ cost;
    }

    @Override
    public Integer estimatedTimeToComplete() {
        return meal.estimatedTimeToComplete() + estimatedTimeToComplete;
    }

}
