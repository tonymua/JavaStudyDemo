package CGLIBProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class SellProxyFactory implements MethodInterceptor {
    // 关联真实对象，控制真实对象的访问
    private Object object;

    // 从代理工厂获取一个代理对象实例，等价于创建小红代理
    public Object getProxyInstance(Object object) {
        this.object = object;
        Enhancer enhancer = new Enhancer();
        // 设置需要增强类的类加载器
        enhancer.setClassLoader(object.getClass().getClassLoader());
        // 设置被代理类，真实对象
        enhancer.setSuperclass(object.getClass());
        // 设置方法拦截器，代理工厂
        enhancer.setCallback(this);
        // 创建代理类
        return enhancer.create();

    }

    private void doSomethingBefore() {
        System.out.println("执行方法前额外的操作...");
    }

    private void doSomethingAfter() {
        System.out.println("执行方法后额外的操作...");
    }

    /**
     * @param o 被代理对象
     * @param method 被拦截的方法
     * @param objects 被拦截方法的所有入参值
     * @param methodProxy 方法代理，用于调用原始的方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doSomethingBefore();
        Object invokeSuperObject = methodProxy.invokeSuper(o, objects);
        doSomethingAfter();
        return invokeSuperObject;
    }
}
