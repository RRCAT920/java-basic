package behavioral_type.template_method.house;

/**
 * @author huzihao
 * @since 2020/11/9 15:54
 */
public abstract class HouseTemplate {
    public final void build() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("房子已建好");
    }

    private void buildFoundation() {
        System.out.println("建地基");
    }

    public abstract void buildPillars();

    public abstract void buildWalls();

    private void buildWindows() {
        System.out.println("建玻璃窗");
    }
}
