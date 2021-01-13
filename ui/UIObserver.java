package ModernProgParadigms.ui;

import ModernProgParadigms.ordermodels.Order;

public interface UIObserver {

    void newOrder(Order order);

    void newWorker(String workerType, Integer id);

    void startWorking();

}
