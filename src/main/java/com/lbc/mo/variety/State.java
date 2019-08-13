package com.lbc.mo.variety;

public abstract class State {

    protected Integer value;

    public abstract void onEnterState();

    public abstract void observe();

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
