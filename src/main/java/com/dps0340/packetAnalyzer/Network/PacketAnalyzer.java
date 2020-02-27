package com.dps0340.packetAnalyzer.Network;

import org.pcap4j.core.*;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;

import java.io.IOException;


public class PacketAnalyzer {
    private PcapNetworkInterface pcapNetworkInterface = null;

    public void initNetworkDevice() {
        try {
            pcapNetworkInterface = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNetworkDevice(PcapNetworkInterface pcapNetworkInterface) {
        this.pcapNetworkInterface = pcapNetworkInterface;
    }

    public PcapNetworkInterface getNetworkInterface() {
        return pcapNetworkInterface;
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        PacketAnalyzer packetAnalyzer = new PacketAnalyzer();

        packetAnalyzer.initNetworkDevice();
        if (packetAnalyzer.pcapNetworkInterface == null) {
            System.out.println("장치가 없습니다.");
            System.exit(1);
        }

        int snapshotLength = 65536;
        int readTimeout = 60;          
        PcapHandle handle = packetAnalyzer.pcapNetworkInterface.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);
        PacketListener listener = new PacketListener() {
            @Override
            public void gotPacket(Packet packet) {
                System.out.println(handle.getTimestamp());
                System.out.println(packet);
            }
        };

        try {
            int maxPackets = 50;
            handle.loop(maxPackets, listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        handle.close();
    }

}