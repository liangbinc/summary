package com.lbc.mo.variety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LackState extends State {
    private static final Logger LOGGER = LoggerFactory.getLogger(LackState.class);

    private Content content;


    public LackState(Content content) {
        this.content = content;
        this.value = 2;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("the job {} queue due to lack of resource!", content.getAppId());
    }

    @Override
    public void observe() {

    }

    @Override
    public Integer getValue() {
        return value;
    }

}
