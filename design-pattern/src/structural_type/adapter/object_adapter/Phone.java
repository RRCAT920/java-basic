package structural_type.adapter.object_adapter;

/**
 * @author huzihao
 * @since 2020/11/2 16:31
 */
public class Phone {
    public void charging(Voltage5 voltage5) {
        var voltage = voltage5.output5V();
        if (voltage == 5) {
            System.out.println("电压为5V，正在充电");
        } else if (voltage > 5) {
            System.out.println("电压大于5V，不能充电");
        }
    }
}
