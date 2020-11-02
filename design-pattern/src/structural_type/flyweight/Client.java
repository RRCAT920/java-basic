package structural_type.flyweight;

/**
 * @author huzihao
 * @since 2020/11/2 22:27
 */
public class Client {
    public static void main(String[] args) {
        var tom = new User();
        tom.setName("Tom");

        var jack = new User();
        jack.setName("Jack");

        var newsWebSite = WebSiteFactory.getWebSite("新闻");
        newsWebSite.use(tom);

        var blogWebSite = WebSiteFactory.getWebSite("博客");
        blogWebSite.use(tom);

        var blogWebSite2 = WebSiteFactory.getWebSite("博客");
        blogWebSite2.use(jack);

        var blogWebSite3 = WebSiteFactory.getWebSite("博客");
        blogWebSite3.use(jack);

        var blogWebSite4 = WebSiteFactory.getWebSite("博客");
        blogWebSite4.use(tom);

        var wechatWebSite = WebSiteFactory.getWebSite("微信公众号");
        wechatWebSite.use(tom);

        System.out.println(WebSiteFactory.size());
    }
}
