package ModernProgParadigms.ui;

public interface UI {

    void render();

    void newOrder();

    void newWorker();

    void startWorking();

    void addUIObserver(UIObserver observer);

    void removeUIObserver(UIObserver observer);
}
