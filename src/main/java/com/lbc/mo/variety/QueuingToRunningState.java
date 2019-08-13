package com.lbc.mo.variety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueuingToRunningState extends State {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueuingToRunningState.class);

    private Content content;


    public QueuingToRunningState(Content content) {
        this.content = content;
        this.value = 3;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("Your job {}'s state has transitioned to RUNNING!", content.getAppId());
    }

    @Override
    public void observe() {

    }

    @Override
    public Integer getValue() {
        return value;
    }


}
