package com.goorg.goorgjava.util.creator.creators;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreator implements Creator<Activity> {
    @Override
    public Activity createValidItem() {
        return new Activity(1L, "atividade", "descrição", null, null, null, new PriorityTag("tag"), new Workspace());
    }

    @Override
    public List<Activity> createValidItemsList() {
        List<Activity> list = new ArrayList<Activity>();
        list.add(new Activity(1L, "atividade", "descrição", null, null, null, null, new Workspace()));
        list.add(new Activity(2L, "atividade2", "descrição", null, null, null, null, new Workspace()));
        list.add(new Activity(3L, "atividade3", "descrição", null, null, null, null, new Workspace()));

        return list;
    }
}
