package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.model.activity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements ICrudMapper<Task, TaskDto>{
    @Override
    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setActivity(taskDto.getActivity());
        task.setTitle(taskDto.getTitle());
        task.setId(taskDto.getId());
        task.setComplete(taskDto.isComplete());
        task.setFromPomodoro(taskDto.isFromPomodoro());

        return task;
    }

    @Override
    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setActivity(task.getActivity());
        taskDto.setTitle(task.getTitle());
        taskDto.setId(task.getId());
        taskDto.setComplete(task.isComplete());
        taskDto.setFromPomodoro(task.isFromPomodoro());

        return taskDto;
    }
}
