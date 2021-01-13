package ModernProgParadigms.workerslogic;

import ModernProgParadigms.workerslogic.assignstrategy.AssignStrategy;
import ModernProgParadigms.ordermodels.Job;
import ModernProgParadigms.workermodels.Worker;
import ModernProgParadigms.workerslogic.iteration.Iterable;
import ModernProgParadigms.workerslogic.iteration.Iterator;
import ModernProgParadigms.workerslogic.iteration.WorkersPoolIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkersPool implements Iterable<Worker>, Observer<Worker> {

    private final static Logger logger = Logger.getLogger(WorkersPool.class.getName());
    private AssignStrategy assignStrategy;
    private List<Worker> availableWorkers;
    private List<Worker> busyWorkers;
    private File workersSource;

    public WorkersPool(AssignStrategy assignStrategy, File workersSource) {
        this.assignStrategy = assignStrategy;
        this.availableWorkers = new ArrayList<>();
        this.busyWorkers = new ArrayList<>();
        // in real-world scenario I would read here the workers saved in workersSource, it's should be relatively easy
        // since they are serialized using the built-in java Serializable interface
    }

    public void assign(Job job) {
        assignStrategy.assign(job, this.availableWorkers, this.busyWorkers);
    }

    public void addWorker(Worker worker) {
        if (worker.isAvailable()) {
            this.availableWorkers.add(worker);
        } else {
            this.busyWorkers.add(worker);
        }
        worker.addObserver(this);
    }

    public void removeWorker(Worker worker) {
        if (worker.isAvailable()) {
            this.availableWorkers.remove(worker);
        } else {
            this.busyWorkers.remove(worker);
        }
        worker.removeObserver(this);
    }

    @Override
    public Iterator<Worker> iterator() {
        return new WorkersPoolIterator(availableWorkers, busyWorkers);
    }

    @Override
    public void update(Worker observable) {
        if (observable.isAvailable()) {
            this.busyWorkers.remove(observable);
            this.availableWorkers.add(observable);
            logger.log(Level.INFO, "Worker id: " + observable.getId().toString() +
                    " became part of available workers");
        } else {
            this.availableWorkers.remove(observable);
            this.busyWorkers.add(observable);
            logger.log(Level.INFO, "Worker id: " + observable.getId().toString() +
                    " became part of busy workers");
        }
    }
}
