package com.goorg.goorgjava.model.atividade;

import com.goorg.goorgjava.enums.Fase;
import com.goorg.goorgjava.model.atividade.gerenciador.GerenciadorTarefas;
import com.goorg.goorgjava.model.workspace.Workspace;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Atividade implements GerenciadorTarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;
    private String descricao;

    @ElementCollection
    private List<String> anotacoes;
    private LocalDate dataInicio;
    private LocalDate dataFinal;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "atividade")
    private List<Tarefa> tarefas;

    @ManyToOne
    private TagDePrioridade tagDePrioridade;

    @ManyToOne
    private Workspace workspace;

    @Enumerated(value = EnumType.STRING)
    private Fase fase;

    public Atividade(){
        this.fase = Fase.TO_DO;
        this.tarefas = new ArrayList<>();
    }

    public Atividade(Long id, String titulo, String descricao, List<String> anotacoes, LocalDate dataInicio, LocalDate dataFinal, TagDePrioridade tagDePrioridade, Workspace workspace) {
        this();
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.anotacoes = anotacoes;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.tagDePrioridade = tagDePrioridade;
        this.workspace = workspace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(id, atividade.id) && Objects.equals(titulo, atividade.titulo);
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", anotacoes=" + anotacoes +
                ", dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", tarefas=" + tarefas +
                ", tagDePrioridade=" + tagDePrioridade +
                ", workspace=" + workspace +
                ", fase=" + fase +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, anotacoes, dataInicio, dataFinal, tarefas, tagDePrioridade, workspace, fase);
    }

    @Override
    public void addTarefa(Tarefa tarefa) {
        if(tarefa != null){
            this.tarefas.add(tarefa);
        }
    }

    @Override
    public void removeTarefa(Tarefa tarefa) {
        this.tarefas.remove(tarefa);
    }

    @Override
    public void updateTarefa(Tarefa tarefa) throws IndexOutOfBoundsException {
        Optional<Tarefa> tarefaAntiga = this.getTarefaPorId(tarefa.getId());
        if (tarefaAntiga.isPresent()){
            this.removeTarefa(tarefaAntiga.get());
            this.addTarefa(tarefa);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void iniciar(){
        this.fase = Fase.DOING;
    }

    public void completar(){
        this.fase = Fase.DONE;
    }

    public void reiniciar(){
        this.fase = Fase.TO_DO;
    }

    public Optional<Tarefa> getTarefaPorId(Long id){
        for(Tarefa tarefa: this.tarefas){
            if (tarefa.getId().equals(id)) return Optional.of(tarefa);
        }
        return Optional.empty();
    }

    public void completarTarefa(Tarefa tarefa){

    }

    public Fase getStatus(){
        return this.fase;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getAnotacoes() {
        return anotacoes;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public TagDePrioridade getTagDePrioridade() {
        return tagDePrioridade;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public Fase getFase() {
        return fase;
    }
}
