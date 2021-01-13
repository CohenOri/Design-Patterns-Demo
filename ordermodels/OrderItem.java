package ModernProgParadigms.ordermodels;

import ModernProgParadigms.ordermodels.Job;

public interface OrderItem extends Job {

    /**
     * @return detailed description in the menu "Our creamy fish..."
     */
    String description();

    Double cost();

    /**
     * @return how the item called "Coca Cola" etc.
     */
    String title();
}
