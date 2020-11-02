package behavioral_type.template_method;

/**
 * @author huzihao
 * @since 2020/11/3 01:03
 */
public class PureSoybeanMilk extends SoybeanMilk {
    @Override
    protected void addCondiments() {
    }

    @Override
    protected boolean wantCondiments() {
        return false;
    }
}
