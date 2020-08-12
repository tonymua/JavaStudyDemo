package example;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author:
 * @date: created in 9:59 2020/8/3
 * @version:
 */
public class JavaCompilerTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int compilerResult = javaCompiler.run(null, null, null, "D:/test/test.java");
        System.out.println(compilerResult == 0 ? "编译成功" : "编译失败");

        try {
            // 通过Runtime方法调用 JDK1.6之前
            /*Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("java -cp D:/test/test.java");
            InputStream processInputStream = process.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(processInputStream));
            String info="";
            while ((info=bufferedReader.readLine())!=null){
                System.out.println(info);
            }*/

            URL[] urls = new URL[] {new URL("file:/" + "D:/test/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> loadClass = loader.loadClass("test");
            Method main = loadClass.getMethod("main", String[].class);
            // 由于可变参数是JDK5之后才有。代码会编译成：m.invoke（null，"aa","bb")，就发生了参数个数不匹配的问题因此，必须要加上（Object）转型，避免这个问题。
            main.invoke(null, (Object)new String[] {});
            /*Object o = loadClass.newInstance();
            // 调用加载类的sayHello方法
            Method sayHello = loadClass.getMethod("sayHello");
            Object result = sayHello.invoke(o);
            System.out.println(result);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}