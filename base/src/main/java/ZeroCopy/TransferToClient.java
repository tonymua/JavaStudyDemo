package ZeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.springframework.util.StopWatch;

public class TransferToClient {
    public static void main(String[] args) throws IOException {
        TransferToClient transferToClient = new TransferToClient();
        transferToClient.send();
    }

    private void send() throws IOException {
        InetSocketAddress server = new InetSocketAddress("127.0.0.1",8888);
        SocketChannel channel = SocketChannel.open();
        channel.connect(server);
        channel.configureBlocking(true);
        String fileName = "D:\\Downloads\\test.xz";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long size = fileChannel.size();
        int n = 0;
        while (n<size){
            long numberBytes = fileChannel.transferTo(n, size, channel);
            n = n+8388608;
            System.out.println("实际传输字节数："+numberBytes);
        }
        stopWatch.stop();
        System.out.println("耗时："+stopWatch.getTotalTimeMillis());
    }
}
