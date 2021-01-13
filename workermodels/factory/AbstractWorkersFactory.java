package ModernProgParadigms.workermodels.factory;

import ModernProgParadigms.workermodels.Cook;
import ModernProgParadigms.workermodels.Waiter;

public interface AbstractWorkersFactory {

    Waiter createWaiter(Integer id);

    Cook createCook(Integer id);

}
