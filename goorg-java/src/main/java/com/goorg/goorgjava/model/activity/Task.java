package com.goorg.goorgjava.model.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Task extends BaseEntity {
    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    @NotNull(message = "atividade é obrigatória")
    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public Task(){
        this.complete = false;
    }

    public Task(String title){
        this();
        this.title = title;
    }

    public Task(Long id, String title){
        this(title);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return complete == task.complete && id.equals(task.id) && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, complete);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", complete=" + complete +
                '}';
    }
}
