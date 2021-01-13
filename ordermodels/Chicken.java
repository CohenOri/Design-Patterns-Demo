package ModernProgParadigms.ordermodels;

import ModernProgParadigms.workermodels.WorkerCapabilities;

public class Chicken extends MealDecorator {

    public Chicken(Meal meal) {
        // Here in real-world scenario I would read from a meals file, instead of just hard-coding meals data.
        // Meals file could be edited using the managing UI.
        super(meal, WorkerCapabilities.COOK,
                "and then prepare Chicken and add it\n", " Our famous Chicken ",
                50.0, 10, " with Chicken");
    }
}
