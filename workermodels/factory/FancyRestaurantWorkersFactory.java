package ModernProgParadigms.workermodels.factory;

import ModernProgParadigms.workermodels.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FancyRestaurantWorkersFactory implements AbstractWorkersFactory {

    private Properties p;

    public FancyRestaurantWorkersFactory() {
        try {
            FileReader reader = new FileReader("fancy_restaurant.properties");
            this.p = new Properties();
            this.p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Waiter createWaiter(Integer id) {
        Map<WorkerCapabilities, Boolean> capabilities = new HashMap<>();
        capabilities.put(WorkerCapabilities.COOK, getCapabilityProp("WAITER-COOK"));
        capabilities.put(WorkerCapabilities.WAITER, getCapabilityProp("WAITER-WAITER"));
        capabilities.put(WorkerCapabilities.HOST, getCapabilityProp("WAITER-HOST"));
        capabilities.put(WorkerCapabilities.CASHIER, getCapabilityProp("WAITER-CASHIER"));
        return new FancyRestaurantWaiter(id, capabilities);
    }

    @Override
    public Cook createCook(Integer id) {
        Map<WorkerCapabilities, Boolean> capabilities = new HashMap<>();
        capabilities.put(WorkerCapabilities.COOK, getCapabilityProp("COOK-COOK"));
        capabilities.put(WorkerCapabilities.WAITER, getCapabilityProp("COOK-WAITER"));
        capabilities.put(WorkerCapabilities.HOST, getCapabilityProp("COOK-HOST"));
        capabilities.put(WorkerCapabilities.CASHIER, getCapabilityProp("COOK-CASHIER"));
        return new FancyRestaurantCook(id, capabilities);
    }

    private Boolean getCapabilityProp(String capPropString) {
        return Boolean.parseBoolean(p.getProperty(capPropString));
    }
}
