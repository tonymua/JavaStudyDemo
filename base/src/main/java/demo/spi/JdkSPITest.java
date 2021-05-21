package demo.spi;

import java.util.ServiceLoader;

import org.junit.Test;

/**
 * @author:
 * @date: created in 12:36 2021/5/20
 * @version:
 */
public class JdkSPITest {
    @Test
    public void testSayHi(){
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);
    }
}