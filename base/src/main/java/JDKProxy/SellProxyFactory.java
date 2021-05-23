package JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SellProxyFactory implements InvocationHandler {
    // 代理的真实对象
    private Object object;

    public SellProxyFactory(Object object) {
        this.object = object;
    }

    private void doSomethingAfter() {
        System.out.println("执行代理后的额外操作...");
    }

    private void doSomethingBefore() {
        System.out.println("执行代理前的额外操作...");
    }
    /**
     * @param proxy 代理对象
     * @param method 真正执行的方法
     * @param args 调用第二个参数method时传入的参数列表值
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object invokeObject = method.invoke(object, args);
        doSomethingAfter();
        return invokeObject;
    }
}
