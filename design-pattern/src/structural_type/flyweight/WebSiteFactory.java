package structural_type.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huzihao
 * @since 2020/11/2 22:25
 */
public class WebSiteFactory {
    /**
     * Don't let anyone to instantiate this class
     */
    private WebSiteFactory() {
    }

    /**
     * 网站池
     */
    private static final Map<String, WebSite> strWebSiteMap = new HashMap<>();

    /**
     * 根据类型获得网站
     *
     * @param type 发布形式
     * @return 带发布形式的网站
     */
    public static WebSite getWebSite(String type) {
        strWebSiteMap.putIfAbsent(type, new ConcreteWebSite(type));
        return strWebSiteMap.get(type);
    }

    /**
     * 获得池中网站的个数
     *
     * @return 网站个数
     */
    public static int size() {
        return strWebSiteMap.size();
    }
}
