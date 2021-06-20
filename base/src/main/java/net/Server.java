package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress(3001);
        serverSocket.bind(address);
        LinkedList list = new LinkedList();
        while (true){
            Socket client = serverSocket.accept();
            list.add(client);
            System.out.println(list.size());
        }
    }
}
