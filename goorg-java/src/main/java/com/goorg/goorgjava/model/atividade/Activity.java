package com.goorg.goorgjava.model.atividade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.workspace.Workspace;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Activity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    private Long id;

    @NotNull
    private String title;
    private String description;

    @ElementCollection
    private List<String> anotations;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "activity")
    private List<Task> tasks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private PriorityTag priorityTag;


    @NotNull(message = "workspace é obrigatório")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Workspace workspace;

    @Enumerated(value = EnumType.STRING)
    private Phase phase;

    public Activity(){
        this.phase = Phase.TO_DO;
        this.tasks = new ArrayList<>();
    }

    public Activity(Long id, String title, String description, List<String> anotations, LocalDate startDate, LocalDate endDate, PriorityTag priorityTag, Workspace workspace) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
        this.anotations = anotations;
        this.startDate = startDate;
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

    public void iniciar(){
        this.phase = Phase.DOING;
    }

    public void completar(){
        this.phase = Phase.DONE;
    }

    public void reiniciar(){
        this.phase = Phase.TO_DO;
    }

    public Optional<Task> getTarefaPorId(Long id){
        for(Task task : this.tasks){
            if (task.getId().equals(id)) return Optional.of(task);
        }
        return Optional.empty();
    }

    public void completarTarefa(Task task){

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnotations() {
        return anotations;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public PriorityTag getPriorityTag() {
        return priorityTag;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public Phase getPhase() {
        return phase;
    }

    public Long getWorkspaceId() {
        return this.workspace.getId();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnotations(List<String> anotations) {
        this.anotations = anotations;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public void setPriorityTag(PriorityTag priorityTag) {
        this.priorityTag = priorityTag;
    }
}
