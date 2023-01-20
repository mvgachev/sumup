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
                    collectTasks(currentTaskRequest, taskResponseList);
                }
            }
        }
        return taskResponseList;
    }

    private void collectTasks(TaskRequest currentTaskRequest, List<TaskResponse> taskResponseList) {
        //Check if task can be executed or requires another one to be executed first
        if (currentTaskRequest.getRequires() != null && !currentTaskRequest.getRequires().isEmpty()) {
            for (String require : currentTaskRequest.getRequires()) {
                if (availableTasks.containsKey(require)) {
                    collectTasks(availableTasks.get(require), taskResponseList);
                }
            }
        }
        //Add the task to the list
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
