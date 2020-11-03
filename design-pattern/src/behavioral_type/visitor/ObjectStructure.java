package behavioral_type.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 16:56
 */
public class ObjectStructure {
    private final List<Person> personList = new LinkedList<>();

    public void add(Person person) {
        personList.add(person);
    }

    public void remove(Person person) {
        personList.remove(person);
    }

    public void display(Action action) {
        personList.forEach(person -> person.accept(action));
    }
}
