package com.company;

import java.io.*;

import org.junit.Test;

public class FileStream {
    @Test
    public void readFile()
        throws IOException {
        /*
         * java.io.InputStream inputStream = null; try { inputStream = new FileInputStream("src/readme.txt"); int n; while ((n =
         * inputStream.read()) != -1) { System.out.println(n); } } finally { if (inputStream != null) { inputStream.close(); } }
         */
        try (java.io.InputStream inputStream =
            new FileInputStream("src/readme.txt")) {
            /*
             * int n; while ((n = inputStream.read()) != -1) { System.out.println(n);
             */
            int n;
            StringBuffer stringBuffer = new StringBuffer();
            while ((n = inputStream.read()) != -1) {
                stringBuffer.append((char)n);
                // System.out.println("read " + n + " bytes");
            }
            System.out.println(stringBuffer.toString());
        }
    }
    @Test
    public void writeFile()
        throws IOException {
        try (OutputStream outputStream =
            new FileOutputStream("src/readme.txt")) {
            outputStream.write("Hello!".getBytes("utf-8"));
        }
    }
    
    @Test
    public void copy()
        throws IOException {
        try (InputStream inputStream = new FileInputStream("src/readme.txt");
            OutputStream outputStream =
                new FileOutputStream("src/readme_copy.txt")) {
            byte[] buffer = new byte[1024];
            inputStream.read(buffer);
            outputStream.write(buffer);
        }
    }
    
    @Test
    public void classpath()
        throws IOException {
        try (InputStream inputStream = new FileInputStream("src/readme.txt")) {
            if (inputStream != null) {
                BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }
                System.out.println(stringBuffer.toString());
            }
        }
    }
}