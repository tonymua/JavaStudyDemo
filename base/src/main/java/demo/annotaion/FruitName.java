package demo.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
/*
标记注解，表示是否将注解信息添加在Java文档中(JavaDoc)
 */
@Target(ElementType.PARAMETER)
/*
表示该注解作用在什么地方
FIELD:成员变量，对象，属性(包括enum实例)
CONSTRUCTOR:构造器
LOCAL_VARIABLE:成员变量
METHOD:方法
PACKAGE:包
PARAMETER:参数
TYPE:类，接口(包括注解类型)或enum声明


 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
/*
生命周期:SOURCE编译阶段丢弃，不会写入字节码 @Override，@SuppressWarnings
        CLASS在类加载的时候丢弃，字节码处理中有用，默认方式
        RUNTIME始终不会丢弃，运行期也会保留该注解，可以通过反射获取注解的信息，自定义注解通常使用该方式

 */
public @interface FruitName {
    String value() default "";
}
