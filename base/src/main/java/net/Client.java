package net;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList clients = new LinkedList();
        for (int i = 0; i < 100000; i++) {
            Socket client = new Socket("127.0.0.1",3001);
            clients.add(client);
        }
        Thread.sleep(10000000);
    }
}
