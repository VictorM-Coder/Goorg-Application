package com.goorg.goorgjava.util.creator.creators;

import com.goorg.goorgjava.model.activity.Activity;
import com.goorg.goorgjava.model.activity.PriorityTag;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreator implements Creator<Activity> {
    @Override
    public Activity createValidItem() {
        return new Activity(1L, "atividade", "descrição", new ArrayList<>(), null, new PriorityTag("tag"), new Workspace());
    }

    @Override
    public List<Activity> createValidItemsList() {
        return List.of(new Activity(1L, "atividade", "descrição", new ArrayList<>(), null, new PriorityTag(), new Workspace()));
    }
}
