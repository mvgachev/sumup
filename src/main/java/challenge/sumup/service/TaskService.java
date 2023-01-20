package challenge.sumup.service;

import java.util.List;

import challenge.sumup.model.TaskRequest;
import challenge.sumup.model.TaskResponse;

public interface TaskService {

    List<TaskResponse> getSortedTasks(List<TaskRequest> taskRequestList);

}
