package com.goorg.goorgjava.util.creator.creators;

import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public class PriorityTagCreator implements Creator<PriorityTag> {
    @Override
    public PriorityTag createValidItem() {
        return new PriorityTag(1L, "Tag 1");
    }

    @Override
    public List<PriorityTag> createValidItemsList() {
        return new ArrayList<>(List.of(new PriorityTag(1L,"tag 1"),new PriorityTag(2L,"tag 2"), new PriorityTag(3L,"tag 3") ));
    }
}
