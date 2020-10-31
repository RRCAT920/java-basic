package factory.order.abstract_factory;

import factory.pizza.Pizza;

/**
 * 抽象工厂模式：定义一个接口，用于创建相关和依赖的对象簇，而不是指定具体类
 *
 * @author huzihao
 * @since 2020/10/31 13:17
 */
public interface AbstractFactory {
    Pizza createPizza(String type);
}
