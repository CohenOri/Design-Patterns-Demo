package ModernProgParadigms.workermodels;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FancyRestaurantWaiter extends Waiter {

    private final static Logger logger = Logger.getLogger(FancyRestaurantWaiter.class.getName());

    public FancyRestaurantWaiter(Integer id, Map<WorkerCapabilities, Boolean> capabilities) {
        super(id, capabilities);
    }

    @Override
    public void offerToOrder() {
        logger.log(Level.INFO,
                "FancyRestaurantWaiter id: {0} has a suite & mustache and he just offered politely to order",
                super.getId().toString());
    }
}
