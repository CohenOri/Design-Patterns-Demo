package ModernProgParadigms.workermodels;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Waiter extends Worker {

    private final static Logger logger = Logger.getLogger(Waiter.class.getName());

    public Waiter(Integer id, Map<WorkerCapabilities, Boolean> capabilities) {
        super(id, capabilities);
    }

    public void offerToOrder() {
        logger.log(Level.INFO, "Waiter id: {0} just offered to order", super.getId().toString());
    }

}
