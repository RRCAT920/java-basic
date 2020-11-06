package structural_type.composite.university;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/2 19:18
 */
public class College extends Organization {
    private final List<Organization> organizationList = new ArrayList<>();

    public College(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.printf("----%s----%n", getName());
        organizationList.forEach(Organization::print);
    }

    @Override
    public void add(Organization organization) {
        organizationList.add(organization);
    }

    @Override
    public void remove(Organization organization) {
        organizationList.remove(organization);
    }
}
