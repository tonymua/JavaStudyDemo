package ZeroCopy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TransferToServer {
    ServerSocketChannel listenerChannel = null;
    InetSocketAddress server = null;

    public static void main(String[] args) throws IOException {
        TransferToServer transferToServer = new TransferToServer();
        transferToServer.setUp();
        transferToServer.read();
    }

    private void setUp()  {
        try {
            server = new InetSocketAddress(8888);
            listenerChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = listenerChannel.socket();
            serverSocket.bind(server);
            System.out.println("监听端口：" + server.toString());
        }catch (IOException e){
            System.out.println("Failed to bind, is port : "+ server.toString()
                    + " already in use ? Error Msg : "+e.getMessage());
            e.printStackTrace();
        }

    }

    private void read() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        try {
            FileOutputStream out = new FileOutputStream(new File("D:\\Downloads\\test1111.xz"));
            FileChannel outChannel = out.getChannel();
            while (true) {
                SocketChannel coon = null;
                byteBuffer.clear();
                coon = listenerChannel.accept();
                System.out.println("接收：" + coon);
                coon.configureBlocking(true);
                int read = 0;
                while (read != -1) {
                    try {
                        read = coon.read(byteBuffer);
                        //将buffer 指针指向头部
                        byteBuffer.flip();
                        outChannel.write(byteBuffer);
                    } catch (IOException e) {
                        listenerChannel.close();
                        read = -1;
                        e.printStackTrace();

                    }
                    byteBuffer.rewind();
                }
            }
        } catch (IOException e) {
            try {
                listenerChannel.close();
            } catch (IOException ioException) {

            }
            e.printStackTrace();
        }
    }
}
