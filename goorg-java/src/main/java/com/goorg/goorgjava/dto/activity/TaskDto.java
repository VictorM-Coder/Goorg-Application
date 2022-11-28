package com.goorg.goorgjava.dto.activity;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.atividade.Activity;

import javax.validation.constraints.NotNull;

public class TaskDto extends BaseEntityDto {
    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    private Activity activity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
