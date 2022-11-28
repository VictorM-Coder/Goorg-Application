package com.goorg.goorgjava.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.model.workspace.Workspace;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class ActivityDto extends BaseEntityDto {
    @NotNull(message = "título é obrigatório")
    private String title;
    private String description;
    private List<String> anotations;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Task> tasks;
    private PriorityTag priorityTag;
    @NotNull(message = "workspace é obrigatório")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Workspace workspace;
    private Phase phase;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAnotations() {
        return anotations;
    }

    public void setAnotations(List<String> anotations) {
        this.anotations = anotations;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public PriorityTag getPriorityTag() {
        return priorityTag;
    }

    public void setPriorityTag(PriorityTag priorityTag) {
        this.priorityTag = priorityTag;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
