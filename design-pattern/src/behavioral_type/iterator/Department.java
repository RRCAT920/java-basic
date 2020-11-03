package behavioral_type.iterator;

/**
 * @author huzihao
 * @since 2020/11/3 20:33
 */
public class Department {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
