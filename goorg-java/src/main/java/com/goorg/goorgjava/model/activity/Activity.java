package com.goorg.goorgjava.model.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.BaseEntity;
import com.goorg.goorgjava.model.workspace.Workspace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@Entity
public class Activity extends BaseEntity{
    @NotNull
    private String title;
    private String description;

    @ElementCollection
    private List<String> anotations;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @ManyToOne()
    private PriorityTag priorityTag;


    @NotNull(message = "workspace é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Workspace workspace;

    @Enumerated(value = EnumType.STRING)
    private Phase phase;

    public Activity(){
        this.phase = Phase.TO_DO;
        this.tasks = new ArrayList<>();
        this.startDate = LocalDate.now();
    }

    public Activity(Long id, String title, String description, List<String> anotations, LocalDate endDate, PriorityTag priorityTag, Workspace workspace) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
        this.anotations = anotations;
        this.endDate = endDate;
        this.priorityTag = priorityTag;
        this.workspace = workspace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(title, activity.title);
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", titulo='" + title + '\'' +
                ", descricao='" + description + '\'' +
                ", anotacoes=" + anotations +
                ", dataInicio=" + startDate +
                ", dataFinal=" + endDate +
                ", tarefas=" + tasks +
                ", tagDePrioridade=" + priorityTag +
                ", workspace=" + workspace +
                ", fase=" + phase +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, anotations, startDate, endDate, tasks, priorityTag, workspace, phase);
    }

    public Optional<Task> getTarefaPorId(Long id){
        for(Task task : this.tasks){
            if (task.getId().equals(id)) return Optional.of(task);
        }
        return Optional.empty();
    }
}
