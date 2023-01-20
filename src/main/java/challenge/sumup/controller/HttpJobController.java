package challenge.sumup.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.sumup.converter.TaskConverter;
import challenge.sumup.model.Job;
import challenge.sumup.model.TaskResponse;
import challenge.sumup.service.TaskService;

@RestController()
@RequestMapping("/job")
public class HttpJobController {

    private final TaskService taskService;

    public HttpJobController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/json", consumes = "application/json", produces = "application/json")
    public List<TaskResponse> executeTaskReturnJson(@RequestBody Job job) {
        return taskService.getSortedTasks(job.getTasks());
    }

    @PostMapping(value = "/bash", consumes = "application/json")
    public String executeTaskReturnBash(@RequestBody Job job) {
        List<TaskResponse> taskResponseList = taskService.getSortedTasks(job.getTasks());
        return TaskConverter.getListOfCommands(taskResponseList);
    }
}
