package ModernProgParadigms.controller;

import ModernProgParadigms.controller.exceptions.NoSuchWorkerType;
import ModernProgParadigms.workermodels.Worker;
import ModernProgParadigms.workermodels.factory.AbstractWorkersFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateWorkerCommand {

    private Map<String, Command> workers;

    public CreateWorkerCommand(AbstractWorkersFactory workersFactory){
        this.workers = new HashMap<>();

        this.workers.put("COOK", workersFactory::createCook);
        this.workers.put("WAITER", workersFactory::createWaiter);
    }

    public Worker createWorker(String workerType, Integer id) throws NoSuchWorkerType {
            Command command = workers.get(workerType);
            if (command == null) {
                throw new NoSuchWorkerType("WorkerType: " + workerType + " is not supported");
            }
            return command.create(id);
    }
}
