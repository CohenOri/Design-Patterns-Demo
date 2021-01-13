package ModernProgParadigms;

import ModernProgParadigms.controller.Controller;
import ModernProgParadigms.controller.RestaurantType;
import ModernProgParadigms.ui.CLI;
import ModernProgParadigms.ui.UI;
import ModernProgParadigms.workerslogic.assignstrategy.AssignStrategy;
import ModernProgParadigms.workerslogic.assignstrategy.AvailableFirstStrategy;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        UI ui = new CLI();
        AssignStrategy strategy = new AvailableFirstStrategy();
        Controller controller = new Controller(
                strategy, new File("demonstration"), ui, RestaurantType.FancyRestaurant);
        controller.runProgram();
    }
}
