package ModernProgParadigms.ui;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUI implements UI {

    protected List<UIObserver> uiObservers;

    public AbstractUI(){
        this.uiObservers = new ArrayList<>();
    }

    @Override
    public void addUIObserver(UIObserver observer) {
        this.uiObservers.add(observer);

    }

    @Override
    public void removeUIObserver(UIObserver observer) {
        this.uiObservers.remove(observer);
    }

}
