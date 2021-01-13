package ModernProgParadigms.workerslogic.iteration;

import ModernProgParadigms.workermodels.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkersPoolIterator implements Iterator<Worker> {

    private List<Worker> workerList = new ArrayList<>();
    private java.util.Iterator<Worker> iterator;

    public WorkersPoolIterator(List<Worker> availableWorkers, List<Worker> busyWorkers){
        this.workerList.addAll(availableWorkers);
        this.workerList.addAll(busyWorkers);
        this.iterator =  this.workerList.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Worker next() throws NoSuchElementException {
        return iterator.next();
    }
}
