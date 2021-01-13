package ModernProgParadigms.workermodels;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook extends Worker {

    private final static Logger logger = Logger.getLogger(Cook.class.getName());

    public Cook(Integer id, Map<WorkerCapabilities, Boolean> capabilities) {
        super(id, capabilities);
    }

    public void cook() {
        logger.log(Level.INFO, "Cook id: {0} just cooked a stunning meal", super.getId().toString());
    }
}
