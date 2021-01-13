package ModernProgParadigms.controller;

import ModernProgParadigms.workermodels.Worker;

public interface Command {

    Worker create(Integer id);
}
