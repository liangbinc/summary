package com.lbc.mo.variety;


public class Content {

    private State state;

    private String appId;

    private String driverState;

    public Content() {
        state = new NormalState(this);
    }

    public void changeStateTo(State newState) {
        StateControl.checkStateTransition(this.appId, state, newState);
        this.state = newState;
        this.state.onEnterState();
    }

    public State getState() {
        return state;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDriverState() {
        return driverState;
    }

    public void setDriverState(String driverState) {
        this.driverState = driverState;
    }
}
