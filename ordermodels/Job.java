package ModernProgParadigms.ordermodels;


import ModernProgParadigms.workermodels.WorkerCapabilities;

public interface Job {
    /**
     * @return Estimated Time To Complete (aka ETC) in seconds
     */
    Integer estimatedTimeToComplete();

    void handle();

    WorkerCapabilities requiredWorkerCapability();

    String title();
}
