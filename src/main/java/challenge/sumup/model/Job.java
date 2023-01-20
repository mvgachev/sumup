package challenge.sumup.model;

import java.util.List;

public class Job {

    private List<TaskRequest> taskRequests;

    public List<TaskRequest> getTasks() {
        return taskRequests;
    }

    public void setTasks(List<TaskRequest> taskRequests) {
        this.taskRequests = taskRequests;
    }
}
