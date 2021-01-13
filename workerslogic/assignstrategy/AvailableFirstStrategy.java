package ModernProgParadigms.workerslogic.assignstrategy;

import ModernProgParadigms.workerslogic.assignstrategy.exceptions.NoWorkersException;
import ModernProgParadigms.workerslogic.assignstrategy.exceptions.WorkersException;
import ModernProgParadigms.ordermodels.Job;
import ModernProgParadigms.workermodels.Worker;
import ModernProgParadigms.workerslogic.assignstrategy.exceptions.NoCapableWorkersException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvailableFirstStrategy implements AssignStrategy {

    private final static Logger logger = Logger.getLogger(AvailableFirstStrategy.class.getName());

    /**
     * First try to assign to the next available worker then try to assign to the next busy one
     * @param job to assign
     * @param availableWorkers list of available workers
     * @param busyWorkers list of busy workers
     */
    @Override
    public void assign(Job job, List<Worker> availableWorkers, List<Worker> busyWorkers) {
        try {
            if (availableWorkers.isEmpty() && busyWorkers.isEmpty()) {
                logger.log(Level.SEVERE, "Tried to assign job but doesn't have any workers");
                throw new NoWorkersException("You have no workers");
            }
            // try to assign to available worker first
            for (Worker w : availableWorkers) {
                if (w.capabaleOf(job)) {
                    w.assign(job);
                    return;
                }
            }
            // fallback to busy worker
            for (Worker w : busyWorkers) {
                if (w.capabaleOf(job)) {
                    w.assign(job);
                    return;
                }
            }
            // otherwise
            logger.log(Level.SEVERE, "Tried to assign job but doesn't have any workers capable of doing it");
            throw new NoCapableWorkersException("You have no workers capable of Job: " + job.title());
        } catch (WorkersException e) {
            System.out.println(e.getMessage());
        }
    }
}
