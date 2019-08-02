package com.lbc.mo.variety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TaskOverState extends State {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskOverState.class);


    private Content content;

    public TaskOverState(Content content) {
        this.content = content;
        this.value = 1;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("Alert: Your job {} has finished on {} with a {} state.", content.getAppId(), new Date(), content.getDriverState());
    }

    @Override
    public void observe() {

    }

    @Override
    public Integer getValue() {
        return value;
    }
}
