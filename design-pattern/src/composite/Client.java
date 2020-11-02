package composite;

/**
 * @author huzihao
 * @since 2020/11/2 19:20
 */
public class Client {
    public static void main(String[] args) {

        Organization electronicEngineeringDept = new Department("电子工程系");
        Organization computerScienceDept = new Department("计算机科学系");

        Organization computerCollege = new College("计算机学院");
        computerCollege.add(electronicEngineeringDept);
        computerCollege.add(computerScienceDept);

        Organization communicationEngineeringDept = new Department("通信工程系");
        Organization informationEngineeringDept = new Department("信息工程系");

        Organization informationEngineeringCollege = new College("信息工程学院");
        informationEngineeringCollege.add(communicationEngineeringDept);
        informationEngineeringCollege.add(informationEngineeringDept);

        Organization university = new University("清华大学");
        university.add(computerCollege);
        university.add(informationEngineeringCollege);

        university.print();
    }
}
