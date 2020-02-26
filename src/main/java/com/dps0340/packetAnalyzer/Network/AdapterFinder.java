package com.dps0340.packetAnalyzer.Network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;


public class AdapterFinder {
    
    private ArrayList<NetworkInterface> list = null;

    public AdapterFinder() {
        try {
            list = initList();
        } catch (SocketException Err) {
            Err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AdapterFinder adapterFinder = new AdapterFinder();
        for(NetworkInterface networkInterface : adapterFinder.getList()) {
            System.out.println(networkInterface.getName());
        }
    }

    public ArrayList<NetworkInterface> getList() {
        return list;
    }

    public void setList(ArrayList<NetworkInterface> list) {
        this.list = list;
    }

    private ArrayList<NetworkInterface> initList() throws SocketException {
        return Collections.list(NetworkInterface.getNetworkInterfaces());
    }
}