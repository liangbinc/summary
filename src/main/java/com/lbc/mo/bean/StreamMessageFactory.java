package com.lbc.mo.bean;

public class StreamMessageFactory {

    private byte[] message;
    private byte magic;
    private StreamType type;

    public StreamMessageFactory(byte magic, byte type, byte[] message) {
        this.magic = magic;
        this.type = StreamType.getByValue(type);
        this.message = message.clone();
    }

    public static byte[] factory(byte magic, byte type, byte[] body) {
        byte[] message = new byte[body.length + 2];
        message[0] = magic;
        message[1] = type;
        System.arraycopy(body, 0, message, 2, body.length);
        return message;
    }

    public byte[] getMessage() {
        if (message == null) {
            return new byte[0];
        }
        return message.clone();
    }

    public byte getMagic() {
        return magic;
    }

    public StreamType getType() {
        return type;
    }

}
