package structural_type.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/2 19:15
 */
public class University extends Organization {
    private final List<Organization> organizationList = new ArrayList<>();

    public University(String name) {
        super(name);
    }

    @Override
    protected void print() {
        System.out.println("----" + getName() + "----");
        organizationList.forEach(Organization::print);
    }

    @Override
    protected void add(Organization organization) {
        organizationList.add(organization);
    }

    @Override
    protected void remove(Organization organization) {
        organizationList.remove(organization);
    }
}
