package com.goorg.goorgjava.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.model.workspace.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Long getWorkspaceId() {
        return this.workspace.getId();
    }

    public String getWorkspaceName(){
        return this.workspace.getName();
    }
}
