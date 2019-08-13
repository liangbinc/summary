package com.lbc.mo.variety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentLostState extends State {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentLostState.class);

    private Content content;

    public AgentLostState(Content content) {
        this.content = content;
        this.value = 4;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("{} agent lost send mail", content.getAppId());
    }

    @Override
    public void observe() {

    }

    @Override
    public Integer getValue() {
        return value;
    }
}
