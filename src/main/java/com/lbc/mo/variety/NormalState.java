package com.lbc.mo.variety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NormalState extends State{

    private static final Logger LOGGER = LoggerFactory.getLogger(NormalState.class);

    private Content content;


    public NormalState(Content content) {
        this.content = content;
        this.value = 0;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("the job {} loaded!", content.getAppId());
    }

    @Override
    public void observe() {

    }

    @Override
    public Integer getValue() {
        return value;
    }
}
