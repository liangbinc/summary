package com.lbc.mo.constants;

public enum UserType {
    LAB_INTER("LAB_INTER"),
    EXTERNAL("EXTERNAL"),
    WHITE_LIST("WHITE_LIST");

    public final String value;

    UserType(String value) {
        this.value = value;
    }
}
