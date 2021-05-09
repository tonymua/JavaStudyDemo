package demo;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * @author:
 * @date: created in 20:46 2020/12/28
 * @version:
 */
public class OOMTest {
    public static final int _1MB = 1024 * 1024;
    static List<byte[]> byteList = new ArrayList<>();

    static void memPrint() {
        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName() + "committed:" + memoryPoolMXBean.getUsage().getCommitted()
                + "used:" + memoryPoolMXBean.getUsage().getUsed());
        }
    }

    private static void oom(HttpExchange exchange) {
        String response = "oom begin!";
        try {
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
        } catch (IOException e) {
            // e.printStackTrace();
        }
        for (int i = 0;; i++) {
            byte[] bytes = new byte[_1MB];
            byteList.add(bytes);
            System.out.println(i + "MB");
            memPrint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
    }

    private static void srv() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(OOMTest::oom);
        server.start();
    }

    public static void main(String[] args) throws IOException {
        srv();
    }
}