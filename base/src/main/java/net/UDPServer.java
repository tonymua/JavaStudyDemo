package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] bytes = new byte[256];
        while (true){
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
            System.out.println("try receive..");
            socket.receive(packet);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(bytes,bytes.length,address,port);
            String received = new String(packet.getData(),0,packet.getLength());
            System.out.println(received);
            socket.send(packet);
        }
    }
}
