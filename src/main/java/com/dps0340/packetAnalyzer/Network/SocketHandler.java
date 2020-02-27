package com.dps0340.packetAnalyzer.Network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class SocketHandler {

    private Socket socket = null;
    private String address = null;
    private SocketAddress socketAddress = null;
    private int port;

    public SocketHandler(String address, int port) {
        this.address = address;
        this.port = port;
        initSocket(this.address, this.port);
    }

    private boolean initSocket(String address, int port) {
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            socketAddress = new InetSocketAddress(inetAddress, port);
            socket = new Socket();
            socket.bind(socketAddress);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean connect() {
        try {
            socket.connect(socketAddress);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close() {
        try {
            socket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeAddress(String address) {
        this.address = address;
        return initSocket(this.address, this.port);
    }

    public boolean changePort(int port) {
        this.port = port;
        return initSocket(this.address, this.port);
    }

    public Socket getSocket() {
        return socket;
    }

    public int getPort() {
        return port;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public String getAddress() {
        return address;
    }

    public InputStreamReader getInputStreamReader() {
        return getInputStreamReader("UTF-8");
    }

    public InputStreamReader getInputStreamReader(String encoding) {
        try {
            return new InputStreamReader(socket.getInputStream(), Charset.forName(encoding));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OutputStreamWriter getOutputStreamWriter() {
        return getOutputStreamWriter("UTF-8");
    }

    public OutputStreamWriter getOutputStreamWriter(String encoding) {
        try {
            return new OutputStreamWriter(socket.getOutputStream(), Charset.forName(encoding));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}