package com.goorg.goorgjava.model.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.goorg.goorgjava.model.atividade.Activity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.PERSIST)
    private List<Activity> activities;

    public Workspace() {
    }

    public Workspace(Long id, String name, String description, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", descricao='" + description + '\'' +
                ", atividades=" + activities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id.equals(workspace.id) && name.equals(workspace.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCountActivities(){
        return this.activities.size();
    }
}
