package ModernProgParadigms.workerslogic.assignstrategy;

import ModernProgParadigms.ordermodels.Job;
import ModernProgParadigms.workermodels.Worker;

import java.util.List;

public interface AssignStrategy {

    /**
     * Assign method to assign given job to worker
     * @param job to assign
     * @param availableWorkers list of available workers
     * @param busyWorkers list of busy workers
     */
    void assign(Job job, List<Worker> availableWorkers, List<Worker> busyWorkers);
}
