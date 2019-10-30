package com.rk.commonmodule.channel;

public interface IChannel {
    int channelOpen(int flag);
    int channelClose(int flag);
    int channelSend(byte[] data, int length);
    int channelReceive(byte[] data, int length);
}
