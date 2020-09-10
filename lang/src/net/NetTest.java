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
             var bytesOut = new ByteArrayOutputStream();) {

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
        String data = "你好，我是UDP发送端。";
        try (var soc = new DatagramSocket()) {
            var packet = new DatagramPacket(data.getBytes(), 0, data.length(),
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
                    String line;
                    while (null != (line = fileRd.readLine())) {
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
            soc.shutdownOutput();

            String line;
            while (null != (line = socRd.readLine())) {  // fixme 使用处理流有bug
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

    /**
     * 编写一个ATM服务器和一个客户端,可以通过客户端输入帐号,密码,然后利用服务器端验证,且能实现ATM存钱,取钱和修改密码的功能
     */
    /**
     * 网络编程
     * 用网络编程编写一个服务端
     * 编写一个客户端，客户端向服务端发送一条信息
     * 服务端在这条信息前面加上“服务端：”后再返给客户端输出。
     */
    /**
     * 网络聊天功能（50分）
     * 用网络编程编写一个服务端（5分）
     * 用网络编写一个客户端（5分）
     * 客户端输入 client，服务端响应 I'SERVER!（40分）
     */
    /**
     * 实现服务器端和客户端的单线通话（50分）
     * 实例化一个服务器端(10分)
     * 实例化一个客户端（10分）
     * 服务器端和客户端相连接（10分）
     * 一旦服务器端接收到客户端的连接，服务器端就向客户端输出“welcom”（20分）
     */
    /**
     * TCP/IP通信协议和IO
     * 实例化一个服务端（10分）
     * 实例化一个客户端，客户端可以向服务端发送一条信息（10分）
     * 用IO向D盘下面创建temp.txt文件（10分）
     * 服务端把接收到客户端的信息写到temp.txt文件中(20分)
     */
    /**
     * 利用UDP编写一个程序,通过这个程序将一个字符串传给另外一台电脑中同样的程序
     */
    /**
     * 利用UDP编写一个程序,通过这个程序将一个字符串传给其他多台电脑中同样的程序
     */
    /**
     * 利用UDP编写一个程序,通过这个程序将键盘输入的一句话传给其他多台电脑中同样的程序
     */
    /**
     * 利用UDP实现一对多聊天,即在一台电脑上输入要说的话,可以在其它多台电脑上展示出来
     */
    /**
     * 利用UDP和TCP实现
     * 服务器利用UDP将自己的IP和端口号发送给客户端
     * 客户端依据服务器段返回的IP和端口号,利用TCP连接到服务器
     */
}
