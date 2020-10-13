package apache_dbutils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.Random;

import javax.sql.DataSource;

import pojo.Customer;

/**
 * @author huzihao
 * @since 2020/10/13 22:01
 */
public class QueryRunnerTest {
    @FunctionalInterface
    interface RunnableThrow {
        void run() throws Exception;
    }

    @FunctionalInterface
    interface ExceptionHandler<T extends Exception> {
        void handle();
    }

    public static class ExceptionCatcher {
        public static void run(RunnableThrow runnableThrow, ExceptionHandler<?>... handlers) {
            try {
                runnableThrow.run();
            } catch (Exception e) {
                if (0 == handlers.length) e.printStackTrace();
                // 根据 抛出异常对象 的类型进行相应处理
                for (var handler : handlers) {
                    if (e.getClass().equals(getActualTypeArgument(handler))) handler.handle();
                }
            }
        }

        private static Type getActualTypeArgument(ExceptionHandler<?> handler) {
            var genericInterface =
                    (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
            return genericInterface.getActualTypeArguments()[0];
        }

        @Test
        void testRun() {
            var handler = new ExceptionHandler<>() {
                @Override
                public void handle() {
                    System.out.println("exception");
                }
            };

            var aioobeHandler = new ExceptionHandler<ArrayIndexOutOfBoundsException>() {

                @Override
                public void handle() {
                    System.out.println("array index out of bounds");
                }
            };

            var npeHandler = new ExceptionHandler<NullPointerException>() {

                @Override
                public void handle() {
                    System.out.println("null");
                }
            };

            ExceptionCatcher.run(() -> {
                switch (new Random().nextInt(3)) {
                    case 0 -> throw new NullPointerException();
                    case 1 -> throw new ArrayIndexOutOfBoundsException();
                    default -> throw new Exception();
                }
            }, handler, aioobeHandler, npeHandler);

            try {
                switch (new Random().nextInt(3)) {
                    case 0 -> throw new NullPointerException();
                    case 1 -> throw new ArrayIndexOutOfBoundsException();
                    default -> throw new Exception();
                }
            } catch (NullPointerException e) {
                System.out.println("exception");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("array index out of bounds");
            } catch (Exception e) {
                System.out.println("null");
            }
        }

        @Test
        void testLessLambda() {
            RunnableThrow runnableThrow = () -> {
                switch (new Random().nextInt(2)) {
                    case 0 -> throw new NullPointerException();
                    case 1 -> throw new ArrayIndexOutOfBoundsException();
                }
            };

            ExceptionHandler<ArrayIndexOutOfBoundsException>
                    nullPointerExceptionExceptionHandler = () -> System.out.println("hello");

            var handler = new ExceptionHandler<NullPointerException>() {
                @Override
                public void handle() {
                    System.out.println("null");
                }
            };

            ExceptionCatcher.run(runnableThrow, nullPointerExceptionExceptionHandler, handler);
        }

        @Test
        void testMoreLambda() {
            ExceptionCatcher.run(() -> {
                switch (new Random().nextInt(2)) {
                    case 0 -> throw new NullPointerException();
                    case 1 -> throw new ArrayIndexOutOfBoundsException();
                }
            }, (ExceptionHandler<ArrayIndexOutOfBoundsException>) () -> {
                System.out.println("hello");
            }, (ExceptionHandler<NullPointerException>) () -> {
                System.out.println("null");
            });
        }
    }

    private static QueryRunner queryRunner;

    static {
        try (var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("druid.properties")) {
            var props = new Properties();
            props.load(in);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
            queryRunner = new QueryRunner(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void insert() {
        ExceptionCatcher.run(() -> {
            var sql = """
                    insert into customers(name, email, birth)
                    values(?, ?, ?)
                    """;
            var affectedRowNumber = queryRunner.update(sql,
                    "李容蓉", "lrr@lrr.com", "1888-01-01");
            System.out.println(affectedRowNumber);
        });
    }

    // BeanHandler是ResultSetHandler的实现类，用于封装表中的一条记录。
    @Test
    void queryCustomer() {
        ExceptionCatcher.run(() -> {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id = ?
                    """;
            var beanHandler = new BeanHandler<>(Customer.class);
            var customer = queryRunner.query(sql, beanHandler, 22);
            System.out.println(customer);
        });
    }

    @Test
    void queryCustomerList() {
        ExceptionCatcher.run(() -> {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var beanListHandler = new BeanListHandler<>(Customer.class);
            var customerList = queryRunner.query(sql, beanListHandler, 22);
            customerList.forEach(System.out::println);
        });
    }

    @Test
    void queryMap() {
        ExceptionCatcher.run(() -> {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var mapHandler = new MapHandler();
            var map = queryRunner.query(sql, mapHandler, 22);
            System.out.println(map);
        });
    }

    @Test
    void queryMapList() {
        ExceptionCatcher.run(() -> {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var mapListHandler = new MapListHandler();
            var mapList = queryRunner.query(sql, mapListHandler, 22);
            System.out.println(mapList);
        });
    }
}
