package ModernProgParadigms.ordermodels;

public interface Meal extends OrderItem {

    String recipe();

    /**
     * @return how the item called "Coca Cola" etc.
     */
    String title();
}
