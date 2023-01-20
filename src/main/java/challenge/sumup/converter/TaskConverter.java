package challenge.sumup.converter;

import java.util.List;
import java.util.stream.Collectors;

import challenge.sumup.model.TaskRequest;
import challenge.sumup.model.TaskResponse;

public class TaskConverter {

    public static TaskResponse convertTaskRequestToResponse(TaskRequest taskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setName(taskRequest.getName());
        taskResponse.setCommand(taskRequest.getCommand());
        return taskResponse;
    }

    public static String getListOfCommands(List<TaskResponse> taskResponseList) {
        List<String> commandList = taskResponseList.stream()
                .map(TaskResponse::getCommand)
                .collect(Collectors.toList());
        return String.join("\n", commandList);
    }
}
