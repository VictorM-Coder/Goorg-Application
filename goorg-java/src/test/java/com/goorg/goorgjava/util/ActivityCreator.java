package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.model.workspace.Workspace;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreator {
    public static Activity createValidActivity(){
        return new Activity(1L, "atividade", "descrição", null, null, null, new PriorityTag("tag"), new Workspace());
    }

    public static Activity createValidActivity(Workspace workspace){
        return new Activity(1L, "atividade", "descrição", null, null, null, new PriorityTag("tag"), workspace);
    }

    public static List<Activity> createValidActivitiesList(){
        List<Activity> list = new ArrayList<Activity>();
        list.add(new Activity(1L, "atividade", "descrição", null, null, null, null, new Workspace()));
        list.add(new Activity(2L, "atividade2", "descrição", null, null, null, null, new Workspace()));
        list.add(new Activity(3L, "atividade3", "descrição", null, null, null, null, new Workspace()));

        return list;
    }

    public static List<Activity> createValidActivitiesList(Workspace workspace){
        List<Activity> list = new ArrayList<Activity>();
        list.add(new Activity(1L, "atividade", "descrição", null, null, null, null, workspace));
        list.add(new Activity(2L, "atividade2", "descrição", null, null, null, null, workspace));
        list.add(new Activity(3L, "atividade3", "descrição", null, null, null, null, workspace));

        return list;
    }
}
