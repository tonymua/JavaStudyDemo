package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * tail实现日志
 *
 * @author: lwy
 * @date: created in 9:33 2020/11/6
 * @version: 1.0.0
 */
public class TestLog {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("tail", "-f", "/tail0.txt");
            // 打印错误日志
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            // 持续读取tail的输出
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sendLogToKafka(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sendLogToKafka(String line) {
        // 模拟发送日志到Kafka，这里只打印一下
        System.out.println(line);

    }
}