package staticProxy;
/*
 * 访问者仅能通过代理对象访问真实目标对象，不可直接访问目标对象
 */
public class XiaoMing {
    public static void main(String[] args) {
        ChanelFactory chanelFactory = new ChanelFactory();
        XiaoHongSellProxy xiaoHongSellProxy = new XiaoHongSellProxy(chanelFactory);
        /*
         * 代理对象并不是真正提供服务的对象，它只是替访问者访问目标对象的一个中间人，
         * 真正提供服务的还是目标对象，而代理对象的作用就是在目标对象提供服务之前或之后能够执行额外的逻辑
         */
        xiaoHongSellProxy.sellPerfume(100);
    }
}
