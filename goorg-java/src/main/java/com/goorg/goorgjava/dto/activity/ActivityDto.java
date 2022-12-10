package com.goorg.goorgjava.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.dto.workspace.WorkspaceDto;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.activity.Task;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ActivityDto extends BaseEntityDto {
    @NotNull(message = "título é obrigatório")
    private String title;
    private String description;
    private List<String> anotations;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Task> tasks;
    private PriorityTagDto priorityTag;
    @NotNull(message = "workspace é obrigatório")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private WorkspaceDto workspace;
    private Phase phase;

    public Long getWorkspaceId() {
        return this.workspace.getId();
    }

    public String getWorkspaceName(){
        return this.workspace.getName();
    }

    public ActivityDto(){
        this.phase = Phase.TO_DO;
        this.tasks = new ArrayList<>();
        this.startDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDto that = (ActivityDto) o;
        return Objects.equals(this.getId(), that.getId()) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(anotations, that.anotations) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(tasks, that.tasks) && Objects.equals(priorityTag, that.priorityTag) && Objects.equals(workspace, that.workspace) && phase == that.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, anotations, startDate, endDate, tasks, priorityTag, workspace, phase);
    }
}
