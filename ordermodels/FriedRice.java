package ModernProgParadigms.ordermodels;

import ModernProgParadigms.workermodels.WorkerCapabilities;

public class FriedRice extends MealDecorator {

    public FriedRice(Meal meal) {
        // Here in real-world scenario I would read from a meals file, instead of just hard-coding meals data.
        // Meals file could be edited using the managing UI.
        super(meal, WorkerCapabilities.COOK,
                "and then prepare FriedRice and add it\n", " With delicious FriedRice ",
                15.0, 10, " with FriedRice");
    }
}
