package ModernProgParadigms.ordermodels;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Order {

    private final static Logger logger = Logger.getLogger(Order.class.getName());
    private List<OrderItem> items;
    private Integer id;

    public Order(Integer id) {
        this.items = new ArrayList<>();
        this.id = id;
    }

    public void addItem(OrderItem item){
        this.items.add(item);
        logger.log(Level.INFO, "Added order item: " + item.title() + " to order: " + id.toString());
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
