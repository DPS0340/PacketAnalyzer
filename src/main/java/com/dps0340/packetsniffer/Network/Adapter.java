package com.dps0340.packetsniffer.Network;

import java.net.NetworkInterface;


public class Adapter {
    private NetworkInterface networkInterface = null;

    public Adapter(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public void setNetworkInterface(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }
}