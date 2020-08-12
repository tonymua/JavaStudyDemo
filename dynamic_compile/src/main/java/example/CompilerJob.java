package example;

import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author:
 * @date: created in 16:22 2020/7/31
 * @version:
 */
@Component
@Configurable
@EnableScheduling
public class CompilerJob {
    private Logger logger = LoggerFactory.getLogger(CompilerJob.class);
    
    private static boolean isExecute = false;
    
    @Test
    @Scheduled(cron = "*/10 * * * * * ")
    public void test() {
        try {
            if (isExecute) {
                return;
            }
            isExecute = true;
            complierAndRun();
        }
        catch (Exception e) {
            logger.error("test error...", e);
        }
    }
    
    public void complierAndRun() {
        try {
            System.out.println(System.getProperty("user.dir"));
            // 动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null,
                null,
                null,
                "-d",
                System.getProperty("user.dir") + "\\target\\classes\\",
                "D:\\test\\AlTest.java");
            if (status != 0) {
                System.out.println("未编译成功！");
            }
            /*
             * Runtime runtime = Runtime.getRuntime(); Process process = runtime.exec("java -cp D:/test/AlTest.java");
             */
            
            // 动态执行
            // 返回与带有给定字符串名的类 或接口相关联的 Class 对象。
            Class<?> aClass = Class.forName("example.AlTest");
            Object o = aClass.newInstance();
            // 返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
            Method sayHello = aClass.getDeclaredMethod("sayHello");
            // 静态方法第一个参数可为null,第二个参数为实际传参
            Object result = sayHello.invoke(o);
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("complierAndRun error...");
        }
    }
}