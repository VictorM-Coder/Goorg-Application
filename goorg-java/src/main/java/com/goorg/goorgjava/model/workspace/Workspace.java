package com.goorg.goorgjava.model.workspace;

import com.goorg.goorgjava.model.atividade.Activity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

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
