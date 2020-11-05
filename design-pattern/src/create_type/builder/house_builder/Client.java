package create_type.builder.house_builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:19
 */
public class Client {
    public static void main(String[] args) {
        var commonHouseBuilder = new CommonHouseBuilder();
        var houseDirector = new HouseDirector(commonHouseBuilder);
        var house = houseDirector.construct();
        System.out.println(house);

        System.out.println("-----------");
        var highHouseBuilder = new HighHouseBuilder();
        houseDirector = new HouseDirector(highHouseBuilder);
        house = houseDirector.construct();
        System.out.println(house);
    }
}
