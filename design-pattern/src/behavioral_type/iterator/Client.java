package behavioral_type.iterator;

import java.util.ArrayList;

/**
 * @author huzihao
 * @since 2020/11/3 21:01
 */
public class Client {
    public static void main(String[] args) {
        var collegeList = new ArrayList<College>();
        var computerCollege = new ComputerCollege();
        var infoCollege = new InfoCollege();
        collegeList.add(computerCollege);
        collegeList.add(infoCollege);

        var output = new OutputImpl(collegeList);
        output.printCollege();
    }
}
