package ModernProgParadigms.workermodels;

import ModernProgParadigms.workerslogic.Observer;
import ModernProgParadigms.ordermodels.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Note: In real-world scenario Implementing Serializable is a must
 * we would like to read/write workers to persistent volume
 */
public abstract class Worker implements Observable {

    private final static Logger logger = Logger.getLogger(Worker.class.getName());
    private Integer id;
    private Map<WorkerCapabilities, Boolean> capabilities; // capabilities map: WorkerCapabilities -> isCapable boolean
    private boolean available;
    private List<Job> jobsList;
    private List<Observer> observersList;

    public Worker(Integer id, Map<WorkerCapabilities, Boolean> capabilities) {
        this.id = id;
        this.capabilities = capabilities;
        this.available = true;
        this.jobsList = new ArrayList<>();
        this.observersList = new ArrayList<>();
    }

    public boolean isAvailable() {
        return available;
    }

    public Integer getId() {
        return id;
    }

    public void work() {
        for (Job job : new ArrayList<>(jobsList)) {
            logger.log(Level.INFO, "Worker id: " + id.toString() + " is Handling job " + job.title());
            job.handle();
            // when job is done
            doneJob(job);
        }
    }

    /**
     * Uses capabilities map: WorkerCapabilities -> isCapable boolean
     * to verify if worker is capable of doing a given job
     * example:
     * WorkerCapabilities.COOK -> True
     * WorkerCapabilities.WAITER -> True
     * means this worker is capable of doing both COOK and WAITER jobs.
     * @param job to verify if worker is capable of
     * @return true if capable otherwise false
     */
    public boolean capabaleOf(Job job) {
        return capabilities.get(job.requiredWorkerCapability());
    }

    public void assign(Job job) {
        this.jobsList.add(job);
        setAvailability(false);
        logger.log(Level.INFO, "Assigned job " + job.title() + " to worker id: " + id.toString());
        work();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observersList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observersList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observersList) {
            o.update(this);
        }
    }

    private void doneJob(Job job) {
        this.jobsList.remove(job);
        logger.log(Level.INFO, "Worker id: {0} just finished job " + job.title(), id.toString());
        if (jobsList.isEmpty()) {
            setAvailability(true);
        }
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
        notifyObservers();
    }

}
