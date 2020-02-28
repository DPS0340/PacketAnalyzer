package com.dps0340.packetAnalyzer.Network;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;


public class PacketAnalyzer implements Runnable {
    private PcapNetworkInterface pcapNetworkInterface = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;
    private int snapshotLength = 65536;
    private int readTimeout = 60;

    public PacketAnalyzer(String networkInterfaceName) throws SocketException, PcapNativeException {
        NetworkInterface networkInterface = NetworkInterface.getByName(networkInterfaceName);
        pcapNetworkInterface = Pcaps.getDevByAddress(
                networkInterface
                        .getInetAddresses()
                        .nextElement()
        );
        byteArrayOutputStream = new ByteArrayOutputStream();
    }


    public void setNetworkDevice(PcapNetworkInterface pcapNetworkInterface) {
        this.pcapNetworkInterface = pcapNetworkInterface;
    }

    public PcapNetworkInterface getNetworkInterface() {
        return pcapNetworkInterface;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }

    @Override
    public void run() {
        try {
            PcapHandle handle = pcapNetworkInterface.openLive(snapshotLength, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, readTimeout);
            PacketListener listener = new PacketListener() {
                @Override
                public void gotPacket(Packet packet) {
                    try {
                        byteArrayOutputStream.write(handle.getTimestamp().toString().getBytes());
                        byteArrayOutputStream.write(packet.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            int maxPackets = 50;
            handle.loop(maxPackets, listener);
        } catch (InterruptedException | PcapNativeException | NotOpenException e) {
            e.printStackTrace();
        }
    }
}