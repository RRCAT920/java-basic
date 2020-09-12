package net;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author huzihao
 * @since 2020/9/9 20:52
 */
public class NetTest {
    @Test
    public void masterInetAddress() {
        try {
            var baidu = InetAddress.getByName("www.baidu.com");
            System.out.println(baidu);
            System.out.println(baidu.getHostName());
            System.out.println(baidu.getHostAddress());

            var localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
            System.out.println(baidu.getHostName());
            System.out.println(baidu.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contentServer() {
        try (var serverSocket = new ServerSocket(9999);
             var socket = serverSocket.accept();
             var in = socket.getInputStream();
             var bytesOut = new ByteArrayOutputStream()) {

            var bytes = new byte[10];
            int length;
            while (-1 != (length = in.read(bytes))) {
                bytesOut.write(bytes, 0, length);
            }

            System.out.print(socket.getInetAddress() + ": ");
            System.out.print(bytesOut.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contentClient() {
        try (var socket = new Socket(InetAddress.getLocalHost(), 9999);
             var out = socket.getOutputStream()) {
            out.write("你好👋，我是客户端。".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本机作为服务器
     */
    @Test
    public void fileServer() {
        var file = new File("Luffy-server.jpg");
        try (var serverSocket = new ServerSocket(9999);
             var socket = serverSocket.accept();
             var is = socket.getInputStream();
             var fos = new FileOutputStream(file);
             var os = socket.getOutputStream()) {
            var buffer = new byte[1024];
            int length;
            // 阻塞式的read，要明确指示结束。
            while (-1 != (length = is.read(buffer))) {
                fos.write(buffer, 0, length);
            }

            os.write("你好，我收到了路飞的图片。".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待 服务器端 传回信息
     */
    @Test
    public void fileClient() {
        var file = new File("Luffy.jpg");
        try (var fis = new FileInputStream(file);
             var socket = new Socket(InetAddress.getLocalHost(), 9999);
             var os = socket.getOutputStream();
             var is = socket.getInputStream();
             var baos = new ByteArrayOutputStream()) {
            var buffer = new byte[1024];
            int length;
            while (-1 != (length = fis.read(buffer))) {
                os.write(buffer, 0, length);
            }
            socket.shutdownOutput();

            while (-1 != (length = is.read(buffer))) {
                baos.write(buffer, 0, length);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverSendServer() {
        var file = new File("Lucy.jpg");
        try (var serverSocket = new ServerSocket(8000);
             var socket = serverSocket.accept();
             var os = socket.getOutputStream();
             var fis = new FileInputStream(file)) {
            var buffer = new byte[1024];
            int length;
            while (-1 != (length = fis.read(buffer))) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverSendClient() {
        var file = new File("Lucy-client.jpg");
        try (var socket = new Socket(InetAddress.getLocalHost(), 8000);
             var is = socket.getInputStream();
             var fos = new FileOutputStream(file)) {
            var buffer = new byte[1024];
            int length;
            while (-1 != (length = is.read(buffer))) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void txtServer() {
        try (var serverSoc = new ServerSocket(8000);
             var incoming = serverSoc.accept();
             var socIn = incoming.getInputStream();
             var socOut = incoming.getOutputStream();
             var bytesOut = new ByteArrayOutputStream()) {
            var bytes = new byte[1024];
            int length;
            while (-1 != (length = socIn.read(bytes))) {
                bytesOut.write(bytes, 0, length);
            }

            socOut.write(bytesOut.toString().toUpperCase().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void txtClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socOut = soc.getOutputStream();
             var socIn = soc.getInputStream();
             var bytesOut = new ByteArrayOutputStream()) {
            socOut.write("hello world!".getBytes());
            soc.shutdownOutput();

            var bytes = new byte[1024];
            int length;
            while (-1 != (length = socIn.read(bytes))) {
                bytesOut.write(bytes, 0, length);
            }

            System.out.println(bytesOut.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sender() {
        try (var soc = new DatagramSocket()) {
            var data = "你好，我是UDP发送端。";
            var bytes = data.getBytes();
            var packet = new DatagramPacket(bytes, 0, bytes.length,
                    InetAddress.getLocalHost(), 8000);
            soc.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void receiver() {
        try (var soc = new DatagramSocket(8000)) {
            var bytes = new byte[64 * 1024];
            var packet = new DatagramPacket(bytes, 0, bytes.length);

            soc.receive(packet);

            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void overViewUrl() throws MalformedURLException {
        var url = new URL("http://localhost:8080/hello.txt?username=tom#a");

        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getQuery());
        System.out.println(url.getFile());
        System.out.println(url.getRef());
    }

    @Test
    public void downloadImage() {
        HttpURLConnection urlCxn = null;
        try {
            var url = new URL("http://localhost:8080/examples/Lucy.jpg");
            urlCxn = (HttpURLConnection) url.openConnection();
            urlCxn.connect();

            var path = url.getPath();
            var originFilename = path.substring(path.lastIndexOf('/') + 1);
            var dotIndex = originFilename.indexOf('.');
            var filename = originFilename.substring(0, dotIndex)
                    + "-download"
                    + originFilename.substring(dotIndex);

            try (var in = urlCxn.getInputStream();
                 var fileOut = new FileOutputStream(filename)) {
                var bytes = new byte[1024];
                int length;
                while (-1 != (length = in.read(bytes))) {
                    fileOut.write(bytes, 0, length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != urlCxn) urlCxn.disconnect();
        }
    }

    /**
     * 编写一个服务器和一个客户端
     * 1)服务器可以读取本地文件内容并能将内容发给请求的客户端
     * 2)再编写一个可以发请求到服务器,并能从服务器段获取文件内容的客户端
     */
    @Test
    public void filesServer() {
        var file = new File("dbcp.txt");
        try (var serverSoc = new ServerSocket(8000);
             var incoming = serverSoc.accept();
             var bufRd = new BufferedReader(new FileReader(file));
             var bufWr = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()))) {
            String line;
            while (null != (line = bufRd.readLine())) {
                bufWr.write(line);
                bufWr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void filesClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var bufRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
             var bufWr = new BufferedWriter(new FileWriter("dbcp-download.txt"))) {
            String line;
            while (null != (line = bufRd.readLine())) {
                bufWr.write(line);
                bufWr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编写一个简单的浏览器
     * 1)将第一题的服务器做调整,如果客户端传输字符串: GET HTTP1.0  /index.jsp,
     * 服务器会将指定目录(路径)下的index.jsp文件传输到客户端
     * 2)客户端可以发请求和把上面的文件保存到本机的制定目录下
     */
    @Test
    public void httpServer() {
        try (var serverSoc = new ServerSocket(8000);
             var incoming = serverSoc.accept();
             var socRd = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
             var socWr = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()))) {
            var req = socRd.readLine();

            System.out.println("hello");
            if ("GET HTTP1.0  /index.jsp".equals(req)) {
                try (var fileRd = new BufferedReader(new FileReader("index.jsp"))) {
                    for (String line; null != (line = fileRd.readLine()); ) {
                        socWr.write(line);
                        socWr.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpClient() {
        var file = new File("receive/index.jsp");
        file.getParentFile().mkdir();
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
             var fileWr = new BufferedWriter(new FileWriter(file))) {
            var req = "GET HTTP1.0  /index.jsp";
            socWr.write(req);
            socWr.flush();
            soc.shutdownOutput();

            // 读操作会刷新输出流的缓存，故此时才进行输出。
            // 所以在读操作之前关闭输出流会抛出异常。
            for (String line; null != (line = socRd.readLine()); ) {
                fileWr.write(line);
                fileWr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编写一个服务器和一个客户端
     * 1)服务器上保存了几个用户名和对应的密码;且能验证客户端发送过来的用户名和密码,是否和保存的某个用户名和对应的密码一致.
     * 2)客户端能连接到服务器，并把用户在键盘上输入的用户名和密码，发送到服务器上
     */
    @Test
    public void loginServer() {
        var nameToPassword = new HashMap<String, String>();
        nameToPassword.put("张三", "123");
        nameToPassword.put("李四", "321");
        nameToPassword.put("王五", "456");
        nameToPassword.put("赵六", "654");

        try (var serverSoc = new ServerSocket(8000);
             var soc = serverSoc.accept();
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
             var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))) {
            var req = socRd.readLine();
            var andIndex = req.indexOf('&');
            var name = req.substring(0, andIndex);
            var password = req.substring(andIndex + 1);

            var rs = name + "，";
            if (password.equals(nameToPassword.get(name))) rs += "登录成功！😄";
            else rs += "登录失败！😔";
            socWr.write(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginClient() {
        System.out.println("尝试连接 127.0.0.1:8000 ...");
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
            // 由于IDEA的单元测试不支持命令行输入，故使用字符串。
            socWr.write("张三&123");
            socWr.flush();
            soc.shutdownOutput();

            System.out.println(socRd.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编写一个ATM服务器和一个客户端,可以通过客户端输入帐号,密码,然后利用服务器端验证,且能实现ATM存钱,取钱和修改密码的功能
     */
    @Test
    public void ATMServer() {
        var map = new HashMap<User, Account>();
        init(map);
        ServerSocket serverSoc = null;
        try {
            serverSoc = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null == serverSoc) return;

        while (true) {
            try (var soc = serverSoc.accept();
                 var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                 var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))) {

                var request = socRd.readLine();
                var user = readUser(request);
                var result = "用户名或密码错误😔";

                if (map.containsKey(user)) {
                    var account = map.get(user);
                    label: if (request.contains("+")) {
                        var amount = Double.parseDouble(readValue(request, "+"));

                        if (amount <= 0) {
                            result = "不能存入非正数，存钱失败。";
                            break label;
                        }
                        account.deposit(amount);
                        result = "成功存入" + amount;
                    } else if (request.contains("-")) {
                        var amount = Double.parseDouble(readValue(request, "-"));

                        if (amount > account.getBalance()) {
                            result = "存款不足，取钱失败。";
                            break label;
                        }
                        account.withdraw(amount);
                        result = "成功取出" + amount;
                    } else if (request.contains("#")){
                        map.remove(user);

                        var password = readValue(request, "#");
                        user.setPassword(password);
                        map.put(user, account);
                        result = "密码修改成功。";
                    } else result = "登录成功😄";
                }

                socWr.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 0.登录✅
    // 1.存钱✅
    // 2.取钱✅
    // 3.修改密码✅
    @Test
    public void ATMClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {

            socWr.write("123&456");
            socWr.flush();
            soc.shutdownOutput();

            var result = socRd.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readValue(String request, String pattern) {
        var index = request.indexOf(pattern);
        return request.substring(index + 1);
    }

    private void init(Map<User, Account> map) {
        map.put(new User("123", "123"), new Account(1000));
    }

    private User readUser(String request) {
        int separatorIndex = request.length();
        if (request.contains("+")) separatorIndex = request.indexOf("+");
        else if (request.contains("-")) separatorIndex = request.indexOf("-");
        else if (request.contains("#")) separatorIndex = request.indexOf("#");

        int andIndex = request.indexOf("&");
        var name = request.substring(0, andIndex);
        var password = request.substring(andIndex + 1, separatorIndex);
        return new User(name, password);
    }

    static class User {
        String id;
        String password;

        public User(String id, String password) {
            this.id = id;
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(id, user.id) &&
                    Objects.equals(password, user.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, password);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class Account {
        double balance;

        public Account(double balance) {
            this.balance = balance;
        }

        public void deposit(double amt) {
            balance += amt;
        }

        public void withdraw(double amt) {
            balance -= amt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return Double.compare(account.balance, balance) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(balance);
        }

        public double getBalance() {
            return balance;
        }
    }

    /**
     * 网络编程
     * 用网络编程编写一个服务端
     * 编写一个客户端，客户端向服务端发送一条信息
     * 服务端在这条信息前面加上“服务端：”后再返给客户端输出。
     */
    @Test
    public void dummyServer() {
        ServerSocket serverSoc = null;
        try {
            serverSoc = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null == serverSoc) return;

        while (true) {
            try (var soc = serverSoc.accept();
                 var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                 var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))) {

                String line = socRd.readLine();
                socWr.write("服务器端：" + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void dummyClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {

            socWr.write("我是容蓉小猫咪🐈");
            socWr.flush();
            soc.shutdownOutput();

            String line = socRd.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络聊天功能（50分）
     * 用网络编程编写一个服务端（5分）
     * 用网络编写一个客户端（5分）
     * 客户端输入 client，服务端响应 I'am Server!（40分）
     */
    @Test
    public void foolServer() {
        ServerSocket serverSoc = null;
        try {
            serverSoc = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null == serverSoc) return;

        while (true) {
            try (var soc = serverSoc.accept();
                 var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                 var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))) {
                String line = socRd.readLine();
                if ("client".equals(line)) socWr.write("I'am Server!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void foolClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
            var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
            var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
            socWr.write("hello");
            socWr.flush();
            soc.shutdownOutput();

            String line = socRd.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现服务器端和客户端的单线通话（50分）
     * 实现一个服务器端(10分)
     * 实现一个客户端（10分）
     * 服务器端和客户端相连接（10分）
     * 一旦服务器端接收到客户端的连接，服务器端就向客户端输出“welcome”（20分）
     */
    @Test
    public void emmmServer() {
        ServerSocket serverSoc = null;
        try {
            serverSoc = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == serverSoc) return;

        while (true) {
            try (var incoming = serverSoc.accept();
                 var socWr = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()))) {
                socWr.write("welcome");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void emmmClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
            System.out.println(socRd.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * TCP/IP通信协议和IO
     * 实现一个服务端（10分）
     * 实现一个客户端，客户端可以向服务端发送一条信息（10分）
     * 用IO向D盘下面创建temp.txt文件（10分）
     * 服务端把接收到客户端的信息写到temp.txt文件中(20分)
     */
    @Test
    public void tempServer() {
        try (var serverSoc = new ServerSocket(8000);
             var incoming = serverSoc.accept();
             var socRd = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
             var fileWr = new BufferedWriter(new FileWriter("temp-server.txt"))) {
            String line;
            while (null != (line = socRd.readLine())) {
                fileWr.write(line);
                fileWr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tempClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
            var fileRd = new BufferedReader(new FileReader("temp-client.txt"));
            var socWr = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))) {
            String line;
            while (null != (line = fileRd.readLine())) {
                socWr.write(line);
                socWr.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用UDP编写一个程序,通过这个程序将一个字符串传给另外一台电脑中同样的程序
     */
    @Test
    public void strSender() {
        try (var soc = new DatagramSocket()) {
            var str = "👋，我是通过UDP协议传输的";
            var bytes = str.getBytes();
            var packet = new DatagramPacket(bytes, 0, bytes.length,
                    InetAddress.getLocalHost(), 8000);
            soc.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void strReceiver() {
        try (var soc = new DatagramSocket(8000)) {
            var buffer = new byte[1 << 16]; // aka 64k
            var packet = new DatagramPacket(buffer, 0, buffer.length);

            soc.receive(packet);

            var str = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用UDP实现一对多聊天,即在一台电脑上输入要说的话,可以在其它多台电脑上展示出来
     */
    @Test
    public void sendToManyHost() {
        try (var soc = new DatagramSocket()) {
            var data = "🆔EA的单元测试有🐛";
            var bytes = data.getBytes();
            var packets = new ArrayList<DatagramPacket>();
            var destPort = 8000;
            for (int i = 0; i < 10; i++) {
                var packet = new DatagramPacket(bytes, 0, bytes.length,
                        InetAddress.getLocalHost(), destPort + i);
                packets.add(packet);
            }

            for (var packet : packets) {
                soc.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // fixme 🆔EA多线程bug
    @Test
    public void tenServer() {
        for (int i = 0; i < 10; i++) {
            final int[] arr = {i};
            new Thread(() -> startupServer(8000 + arr[0])).start();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int[] arr = {i};
            new Thread(() -> startupServer(8000 + arr[0])).start();
        }
    }

    private static void startupServer(int port) {
        try (var soc = new DatagramSocket(port)) {
            var buffer = new byte[1 << 16]; // aka 64k
            var packet = new DatagramPacket(buffer, 0, buffer.length);
            soc.receive(packet);

            var incoming = port + ":" + new String(packet.getData(), 0, packet.getLength());
            System.out.println(incoming);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用UDP和TCP实现
     * 服务器利用UDP将自己的IP和端口号发送给客户端
     * 客户端依据服务器段返回的IP和端口号,利用TCP连接到服务器
     */
    @Test
    public void smartServer() {
        try (var soc = new DatagramSocket()) {
            var localHost = InetAddress.getLocalHost();
            var data = localHost.getHostName() + ":" + 8000;
            var bytes = data.getBytes();
            var packet = new DatagramPacket(bytes, 0, bytes.length, localHost, 7000 );

            soc.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var serverSoc = new ServerSocket(8000);
             var incoming = serverSoc.accept();
             var socWr = incoming.getOutputStream()) {
            socWr.write("连接🔗成功😄".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void smartClient() {
        String addr = null;
        try (var soc = new DatagramSocket(7000)) {
            var buffer = new byte[1 << 16]; // 64k
            var packet = new DatagramPacket(buffer, 0, buffer.length);

            soc.receive(packet);
            addr = new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null == addr) return;
        var separatorIndex = addr.indexOf(":");
        var ip = addr.substring(0, separatorIndex);
        var port = Integer.parseInt(addr.substring(separatorIndex + 1));

        try (var soc = new Socket(ip, port);
             var socRd = soc.getInputStream();
             var bytesOut = new ByteArrayOutputStream()) {
            var buffer = new byte[1024];
            int length;
            while (-1 != (length = socRd.read(buffer))) {
                bytesOut.write(buffer, 0, length);
            }

            System.out.println(bytesOut.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newKeyboardServer() {
        try (var soc = new ServerSocket(8000);
             var incoming = soc.accept();
             var socWr = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()))) {
            socWr.write("New keyboard red mx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newKeyboardClient() {
        try (var soc = new Socket(InetAddress.getLocalHost(), 8000);
             var socRd = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
            var line = socRd.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
