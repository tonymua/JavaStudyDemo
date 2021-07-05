package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        DatagramSocket socket = new DatagramSocket();
        InetAddress localhost = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, localhost, 8888);
        socket.send(packet);
        socket.receive(packet);
        String received = new String(packet.getData(),0, packet.getLength());
        System.out.println(received);
    }
}
