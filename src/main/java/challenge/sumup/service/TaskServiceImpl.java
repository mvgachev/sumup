package challenge.sumup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import challenge.sumup.converter.TaskConverter;
import challenge.sumup.model.TaskRequest;
import challenge.sumup.model.TaskResponse;

@Service
public class TaskServiceImpl implements TaskService{

    private Map<String, TaskRequest> availableTasks;

    @Override
    public List<TaskResponse> getSortedTasks(List<TaskRequest> taskRequestList) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        if(taskRequestList != null && !taskRequestList.isEmpty()) {
            storeTasks(taskRequestList);
            for (TaskRequest currentTaskRequest : taskRequestList) {
                if (availableTasks.containsValue(currentTaskRequest)) {
                    getCommandList(currentTaskRequest, taskResponseList);
                }
            }
        }
        return taskResponseList;
    }

    private void getCommandList(TaskRequest currentTaskRequest, List<TaskResponse> taskResponseList) {
        if (currentTaskRequest.getRequires() != null && !currentTaskRequest.getRequires().isEmpty()) {
            for (String require : currentTaskRequest.getRequires()) {
                if (availableTasks.containsKey(require)) {
                    getCommandList(availableTasks.get(require), taskResponseList);
                }
            }
        }
        taskResponseList.add(TaskConverter.convertTaskRequestToResponse(currentTaskRequest));
        availableTasks.remove(currentTaskRequest.getName());
    }

    private void storeTasks(List<TaskRequest> taskRequestList) {
        availableTasks = new HashMap<>();
        for (TaskRequest taskRequest : taskRequestList) {
            availableTasks.put(taskRequest.getName(), taskRequest);
        }
    }
}
