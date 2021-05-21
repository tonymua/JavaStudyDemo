package demo.spi;

/**
 * @author:
 * @date: created in 12:33 2021/5/20
 * @version:
 */
public class PythonDeveloper implements Developer{
    @Override
    public void sayHi() {
        System.out.println("Hi, I am a Python Developer.");
    }
}