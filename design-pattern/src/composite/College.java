package composite;

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
    protected void print() {
        System.out.printf("----%s----%n", getName());
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
