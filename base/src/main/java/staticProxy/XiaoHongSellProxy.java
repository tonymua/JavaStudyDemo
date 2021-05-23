package staticProxy;

public class XiaoHongSellProxy implements SellPerfume {
    /*
     * 代理对象内部保存对真实目标对象的引用
     */
    private ChanelFactory chanelFactory;
    public XiaoHongSellProxy(ChanelFactory chanelFactory) {
        this.chanelFactory = chanelFactory;
    }

    @Override
    public void sellPerfume(double price) {
        doSomethingBeforeSell();
        chanelFactory.sellPerfume(price);
        doSomethingAfterSell();
    }

    private void doSomethingBeforeSell() {
        System.out.println("小红代理购买香水前的额外操作...");
    }

    private void doSomethingAfterSell() {
        System.out.println("小红代理购买香水后的额外操作...");
    }
}
