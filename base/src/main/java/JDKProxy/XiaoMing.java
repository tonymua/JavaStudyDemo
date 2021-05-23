package JDKProxy;

import java.lang.reflect.Proxy;

import staticProxy.SellPerfume;

public class XiaoMing {
    public static void main(String[] args) {
        // buyChannel();
        buyRedWine();
    }

    static void buyChannel() {
        ChanelFactory chanelFactory = new ChanelFactory();
        SellProxyFactory sellProxyFactory = new SellProxyFactory(chanelFactory);
        SellPerfume sellPerfume = (SellPerfume)Proxy.newProxyInstance(chanelFactory.getClass().getClassLoader(),
            chanelFactory.getClass().getInterfaces(), sellProxyFactory);
        sellPerfume.sellPerfume(100);
    }

    static void buyRedWine() {
        // 实例化一个红酒供应商
        RedWineFactory redWineFactory = new RedWineFactory();
        // 实例化代理工厂，传入红酒供应商引用控制对其的访问
        SellProxyFactory sellProxyFactory = new SellProxyFactory(redWineFactory);
        // 实例化代理对象
        SellWine sellWine = (SellWine)Proxy.newProxyInstance(redWineFactory.getClass().getClassLoader(),
            redWineFactory.getClass().getInterfaces(), sellProxyFactory);
        // 代理售卖红酒
        sellWine.SellWine(100);
    }
}
