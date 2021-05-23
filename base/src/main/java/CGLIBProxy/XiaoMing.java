package CGLIBProxy;

public class XiaoMing {
    public static void main(String[] args) {
        SellProxyFactory sellProxyFactory = new SellProxyFactory();
        //获取一个代理实例
        ChanelFactory chanelFactoryInstance = (ChanelFactory) sellProxyFactory.getProxyInstance(new ChanelFactory());
        chanelFactoryInstance.sellPerfume(100);
    }
}
