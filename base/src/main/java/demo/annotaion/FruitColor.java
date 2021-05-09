package demo.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.LOCAL_VARIABLE)
public @interface FruitColor {
    public enum Color{
        BLUE,
        RED,
        YELLO
    }
    Color fruitColor() default Color.BLUE;

}
