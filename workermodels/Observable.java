package ModernProgParadigms.workermodels;

import ModernProgParadigms.workerslogic.Observer;

public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
