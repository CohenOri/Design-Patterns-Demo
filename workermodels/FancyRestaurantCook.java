package ModernProgParadigms.workermodels;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FancyRestaurantCook extends Cook {

    private final static Logger logger = Logger.getLogger(FancyRestaurantCook.class.getName());

    public FancyRestaurantCook(Integer id, Map<WorkerCapabilities, Boolean> capabilities) {
        super(id, capabilities);
    }

    @Override
    public void cook() {
        logger.log(Level.INFO,
                "FancyRestaurantCook id: {0} just cooked a fancy & expensive stunning meal",
                super.getId().toString());
    }
}
