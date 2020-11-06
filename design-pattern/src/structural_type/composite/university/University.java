package structural_type.composite.university;

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
    public void print() {
        System.out.println("----" + getName() + "----");
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
