package JDKProxy;

/*
 * 红酒供应商
 */
public class RedWineFactory implements SellWine {
    @Override
    public void SellWine(double price) {
        System.out.println("成功售卖一瓶红酒，价格：" + price + "元");
    }
}
