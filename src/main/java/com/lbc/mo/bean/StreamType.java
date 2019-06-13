package com.lbc.mo.bean;

public enum StreamType {
    INTERACTIVE((byte) 0x00), LOG((byte) 0x02), STAT((byte) 0x04), OUTPUT((byte) 0x06);

    private byte value;

    StreamType(byte value) {
        this.value = value;
    }

    public static StreamType getByValue(byte v) {
        switch (v) {
            case 0x00:
                return INTERACTIVE;
            case 0x02:
                return LOG;
            case 0x04:
                return STAT;
            case 0x06:
                return OUTPUT;
        }
        return null;
    }

    public byte getValue() {
        return value;
    }

}
